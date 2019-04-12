package com.delta.auth.Service.impl;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.delta.auth.dao.AutoIdMapper;
import com.delta.auth.dto.AutoId;
import com.delta.auth.dto.TweiUser;
import com.delta.common.constant.GlobalConst;
import com.delta.common.utils.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname BaseServiceImpl
 * @Date 2019/3/26 15:19
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Slf4j
public abstract class BaseServiceImpl<T extends BaseMapper, K> {

    protected T dao;

    @Autowired
    protected AutoIdMapper autoIdMapper;

    public BaseServiceImpl(T dao) {
        this.dao = dao;
    }

    public ServerResponse<K> insertOne(K k, HttpServletRequest request) {
        completeId(k);
        completeUser(k, request);
        int count = dao.insert(k);
        if (count != 1) {
            return ServerResponse.createByErrorMessage("添加失敗");
        }
        return ServerResponse.createBySuccess(k);
    }

    public ServerResponse<K> deleteOne(K k, HttpServletRequest request) {
        EntityWrapper<K> wrapper = getDeleteAndUpdateWrapper(k);
        if (null == wrapper || wrapper.isEmptyOfWhere()) {
            return ServerResponse.createByErrorMessage("刪除條件不能為空");
        }
        int count = dao.delete(wrapper);
        if (count != 1) {
            return ServerResponse.createByErrorMessage("刪除失敗");
        }
        return ServerResponse.createBySuccess(k);
    }

    public ServerResponse<K> updateOne(K k, HttpServletRequest request) {
        EntityWrapper<K> wrapper = getDeleteAndUpdateWrapper(k);
        k = updateDataVersion(k);
        int count = dao.update(k, wrapper);
        if (count != 1) {
            return ServerResponse.createByErrorMessage("更新失敗");
        }
        return ServerResponse.createBySuccess(k);
    }

    public K selectOne(K k) {
        return (K) dao.selectOne(k);
    }

    public List<K> selectList() {
        return dao.selectList(getListWrapper());
    }

    protected EntityWrapper<K> getDeleteAndUpdateWrapper(K k) {
        EntityWrapper<K> wrapper = new EntityWrapper<>();
        Class clazz = k.getClass();
        Field idField = getField(clazz, TableId.class);
        Field dataVersion = null;
        try {
            dataVersion = clazz.getDeclaredField("dataVersion");
            dataVersion.setAccessible(true);
            wrapper.eq("data_version", dataVersion.get(k));
            if (null == idField) {
                log.info(String.format("entity %s is not declare id field", clazz.toString()));
            } else {
                idField.setAccessible(true);
                wrapper.eq(humpToLine(idField.getName()), idField.get(k));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return wrapper;
    }

    protected K updateDataVersion(K k) {
        Class clazz = k.getClass();
        try {
            Field dataVersion = clazz.getDeclaredField("dataVersion");
            dataVersion.setAccessible(true);
            Integer originDataVersion = Integer.parseInt(dataVersion.get(k).toString());
            dataVersion.set(k, originDataVersion + 1);
        } catch (NoSuchFieldException e) {
            log.info(String.format("entity %s no declaredField of dataVersion", clazz.toString()));
            return k;
        } catch (IllegalAccessException e) {
            log.info("field \'dataVersion\' is private");
            return k;
        }
        return k;
    }

    protected EntityWrapper getListWrapper(){
        EntityWrapper wrapper = new EntityWrapper();
        return wrapper;
    }

    private Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 駝峰轉下劃線
     *
     * @param str
     * @return
     */
    private String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 獲取有指定註解的field
     * @param clazz
     * @param annotation
     * @return
     */
    private Field getField(Class clazz, Class annotation) {
        Field[] fields = clazz.getDeclaredFields();
        Object id = null;
        for (Field field : fields) {
            field.setAccessible(true);
            id = field.getAnnotation(annotation);
            if (null != id) {
                return field;
            }
        }
        return null;
    }

    private K completeUser(K k, HttpServletRequest request){
        TweiUser user = (TweiUser) request.getSession().getAttribute(GlobalConst.CURRENT_USER);
        if (null == user) {
            throw new RuntimeException("需要先登錄,才能繼續");
        }
        Class clazz = k.getClass();
        try {
            Field createBy = clazz.getDeclaredField("createBy");
            Field createAt = clazz.getDeclaredField("createAt");
            Field dataVersion = clazz.getDeclaredField("dataVersion");
            createBy.setAccessible(true);
            createBy.set(k, user.getUserid());
            createAt.setAccessible(true);
            createAt.set(k, Calendar.getInstance().getTime());
            dataVersion.setAccessible(true);
            dataVersion.set(k, 0);
        } catch (NoSuchFieldException e) {
            log.info(String.format("%S沒有對應的字段", clazz.toString()));
        } catch (IllegalAccessException e) {
            log.info(String.format("對應的字段為private作用域"));
        }
        return k;
    }
    private K completeId(K k){
        Class clazz = k.getClass();
        Field tableId = null;
        for (Field field : clazz.getDeclaredFields()) {
            if (null != field.getAnnotation(TableId.class)) {
                tableId = field;
                break;
            }
        }
        TableName tableName = (TableName)clazz.getAnnotation(TableName.class);
        AutoId autoId = new AutoId();
        autoId.setTableName(tableName.value());
        autoId.setPrefix(null);
        autoIdMapper.getAutoId(autoId);
        try {
            tableId.setAccessible(true);
            tableId.set(k,autoId.getAutoid());
        } catch (IllegalAccessException e) {
            log.info(String.format("對應的字段為private作用域"));
        }
        return k;
    }
}
