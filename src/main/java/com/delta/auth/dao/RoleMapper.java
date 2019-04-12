package com.delta.auth.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.delta.auth.dto.TweiRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description
 * @Date 2019/3/19 13:56
 * @Author LIZONG.WEI
 */
@Repository
public interface RoleMapper extends BaseMapper<TweiRole> {
    List<TweiRole> getRoles(String userid);

    List<TweiRole> oweRoles(String userid);

    TweiRole getMaxRoles(String userid);
}
