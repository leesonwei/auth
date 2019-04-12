package com.delta.auth.Service;

import com.delta.auth.dto.TweiAuth;
import com.delta.auth.dto.TweiMenu;
import com.delta.auth.dto.TweiMenuVo;

import java.util.List;

/**
 * @Classname MenuService
 * @Date 2019/4/9 15:29
 * @Author LIZONG.WEI
 * @Since 1.8
 */
public interface MenuService extends BaseService<TweiMenu> {
    /**
     * 查找list對象
     *
     * @return
     */
    List<TweiMenuVo> selectList(TweiMenuVo menu);

    /**
     * 查找用戶菜單
     *
     * @return
     */
    List<TweiMenuVo> selectUserMenus(String userid);

    List<TweiAuth> getMenuAuths();
}
