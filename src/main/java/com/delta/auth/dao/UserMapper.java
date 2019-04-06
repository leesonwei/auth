package com.delta.auth.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.delta.auth.dto.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Classname UserMapper
 * @Description
 * @Date 2019/3/19 13:56
 * @Author LIZONG.WEI
 */
@Repository("userDao")
public interface UserMapper extends BaseMapper<User> {

    /**
     * get total user count
     * @return
     */
    int listCount();

}
