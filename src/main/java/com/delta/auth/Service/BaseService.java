package com.delta.auth.Service;

import com.delta.common.utils.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname BaseService
 * @Date 2019/3/26 15:15
 * @Author LIZONG.WEI
 * @Since 1.8
 */
public interface BaseService<T> {
    /**
     * 新增
     *
     * @param t
     * @return
     */
    ServerResponse<T> insertOne(T t, HttpServletRequest request);

    /**
     * 刪除
     *
     * @param t
     * @return
     */
    ServerResponse<T> deleteOne(T t, HttpServletRequest request);

    /**
     * 更新
     *
     * @param t
     * @return
     */
    ServerResponse<T> updateOne(T t, HttpServletRequest request);


    /**
     * 查找單個
     *
     * @param t
     * @return
     */
    T selectOne(T t);

    /**
     * 查找list對象
     *
     * @return
     */
    List<T> selectList();
}
