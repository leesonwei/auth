package com.delta.auth.Service.impl;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.delta.common.utils.ServerResponse;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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

    //public BaseServiceImpl(){ }

    public BaseServiceImpl(T dao) {
        this.dao = dao;
    }

    public ServerResponse<K> insertOne(K k) {
        int count = dao.insert(k);
        if (count != 1) {
            return ServerResponse.createByErrorMessage("添加失敗");
        }
        return ServerResponse.createBySuccess(k);
    }

    public ServerResponse<K> deleteOne(K k) {
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

    public ServerResponse<K> updateOne(K k) {
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
        return dao.selectList(new EntityWrapper<>());
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
}
