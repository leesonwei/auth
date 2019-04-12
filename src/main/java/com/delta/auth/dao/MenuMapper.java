package com.delta.auth.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.delta.auth.dto.TweiMenu;
import com.delta.auth.dto.TweiMenuVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description
 * @Date 2019/3/19 13:56
 * @Author LIZONG.WEI
 */
@Repository
public interface MenuMapper extends BaseMapper<TweiMenu> {
    /**
     * 查找菜單
     * @param menu
     * @return
     */
    List<TweiMenuVo> selectListVo(TweiMenuVo menu);

    /**
     * 查找用戶菜單
     * @param userid
     * @return
     */
    List<TweiMenuVo> selectUserMenu(String userid);
}
