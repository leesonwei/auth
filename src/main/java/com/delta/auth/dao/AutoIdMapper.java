package com.delta.auth.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.delta.auth.dto.AutoId;
import org.springframework.stereotype.Repository;

/**
 * @Classname TweiApiCode
 * @Date 2019.3.22
 * @author lizong.wei
 * @Since 1.8
 */
@Repository("autoIdMapper")
public interface AutoIdMapper extends BaseMapper<AutoId> {

    /**
     *
     * @param autoId
     */
    void getAutoId(AutoId autoId);
}