package com.delta.auth.Service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.delta.auth.Service.MenuService;
import com.delta.auth.dao.AuthMapper;
import com.delta.auth.dao.MenuMapper;
import com.delta.auth.dto.TweiAuth;
import com.delta.auth.dto.TweiMenu;
import com.delta.auth.dto.TweiMenuVo;
import com.delta.common.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname MenuServiceImpl
 * @Date 2019/4/9 15:29
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, TweiMenu> implements MenuService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    public MenuServiceImpl(MenuMapper dao) {
        super(dao);
    }
    @Override
    protected EntityWrapper<TweiMenu> getDeleteAndUpdateWrapper(TweiMenu menu) {
        EntityWrapper<TweiMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("menu_id", menu.getMenuId());
        wrapper.eq("data_version", menu.getDataVersion());
        return wrapper;
    }

    @Override
    public ServerResponse<TweiMenu> deleteOne(TweiMenu menu, HttpServletRequest request) {
        EntityWrapper<TweiMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("menu_parent_id", menu.getMenuId());
        List<TweiMenu> menus = dao.selectList(wrapper);
        if (menus.size()>0) {
            return ServerResponse.createByErrorMessage("該菜單還有子菜單,不能刪除");
        }
        wrapper = getDeleteAndUpdateWrapper(menu);
        if (null == wrapper || wrapper.isEmptyOfWhere()) {
            return ServerResponse.createByErrorMessage("刪除條件不能為空");
        }
        int count = dao.delete(wrapper);
        if (count != 1) {
            return ServerResponse.createByErrorMessage("刪除失敗");
        }
        return ServerResponse.createBySuccess(menu);
    }

    @Override
    public List<TweiMenuVo> selectList(TweiMenuVo menu) {
        return dao.selectListVo(menu);
    }

    @Override
    public List<TweiMenuVo> selectUserMenus(String userid) {
        return dao.selectUserMenu(userid);
    }

    @Override
    public List<TweiAuth> getMenuAuths() {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("auth_type", 1);
        wrapper.isNotNull("auth_url");
        return authMapper.selectList(wrapper);
    }

    @Override
    protected EntityWrapper getListWrapper(){
        EntityWrapper wrapper = new EntityWrapper();
        List<String> columns = new ArrayList<>();
        columns.add("menu_parent_id");
        columns.add("menu_id");
        wrapper.orderDesc(columns);
        return wrapper;
    }
}
