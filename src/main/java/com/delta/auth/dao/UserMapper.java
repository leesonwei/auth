package com.delta.auth.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.delta.auth.dto.TweiUser;
import org.springframework.stereotype.Repository;

/**
 * @Classname UserMapper
 * @Description
 * @Date 2019/3/19 13:56
 * @Author LIZONG.WEI
 */
@Repository
public interface UserMapper extends BaseMapper<TweiUser> {

    /**
     * get total user count
     *
     * @return
     */
    int listCount();

}
