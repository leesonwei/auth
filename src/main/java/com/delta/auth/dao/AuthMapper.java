package com.delta.auth.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.delta.auth.dto.TweiAuth;
import com.delta.auth.dto.TweiUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description
 * @Date 2019/3/19 13:56
 * @Author LIZONG.WEI
 */
@Repository
public interface AuthMapper extends BaseMapper<TweiAuth> {

}
