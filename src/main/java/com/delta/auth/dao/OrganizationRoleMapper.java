package com.delta.auth.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.delta.auth.dto.TweiOrganizationRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description
 * @Date 2019/3/19 13:56
 * @Author LIZONG.WEI
 */
@Repository
public interface OrganizationRoleMapper extends BaseMapper<TweiOrganizationRole> {
    /**
     * 批量新增
     *
     * @param tList
     * @return
     */
    int insertBatch(List<TweiOrganizationRole> tList);

    /**
     * 批量刪除
     *
     * @param tList
     * @return
     */
    int deleteBatch(List<TweiOrganizationRole> tList);

    /**
     * 批量更新
     *
     * @param tList
     * @return
     */
    int updateBatch(List<TweiOrganizationRole> tList);

}
