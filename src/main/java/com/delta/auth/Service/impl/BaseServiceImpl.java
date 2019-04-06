package com.delta.auth.Service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.delta.auth.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @Classname BaseServiceImpl
 * @Date 2019/3/26 15:19
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Slf4j
public abstract class BaseServiceImpl<T extends BaseMapper,K> {

    protected T dao;

    //public BaseServiceImpl(){ }

    public BaseServiceImpl(T dao){
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
        return (K)dao.selectOne(k);
    }

    public List<K> selectList() {
        return dao.selectList(new EntityWrapper<>());
    }

    protected abstract EntityWrapper<K> getDeleteAndUpdateWrapper(K k);

    protected K updateDataVersion(K k){
        Class clazz = k.getClass();
        try {
            Field dataVersion = clazz.getDeclaredField("dataVersion");
            dataVersion.setAccessible(true);
            Integer originDataVersion = Integer.parseInt(dataVersion.get(k).toString());
            dataVersion.set(k,originDataVersion + 1);
        } catch (NoSuchFieldException e) {
            log.info(String.format("entity %s no declaredField of dataVersion", clazz.toString()));
            return k;
        } catch (IllegalAccessException e) {
            log.info("field \'dataVersion\' is private");
            return k;
        }
        return k;
    }
}
