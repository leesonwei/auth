package com.delta.auth.common;

import com.delta.auth.dto.TweiMenuVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname MenuUtil
 * @Date 2019/4/11 11:02
 * @Author LIZONG.WEI
 * @Since 1.8
 */
public class MenuUtil {
    public static List<TweiMenuVo> setActive(String id, List<TweiMenuVo> allMenu){
        for (TweiMenuVo menu:allMenu) {
            if (id.equals(menu.getMenuId())) {
                menu.setActive("active");
            } else {
                menu.setActive("");
            }
        }
        return allMenu;
    }
    public static List<TweiMenuVo> getMenuTree(List<TweiMenuVo> allMenu){
        List<TweiMenuVo> roots = new ArrayList<>();
        for (TweiMenuVo menu:allMenu) {
            if (menu.getMenuParentId().equals("0")) {
                roots.add(menu);
            }
        }
        for (TweiMenuVo menu:roots) {
            List<TweiMenuVo> childs = getChild(menu.getMenuId(), allMenu);
            menu.setSubMenu(childs);
        }
        return roots;
    }

    public static List<TweiMenuVo> getChild(String id, List<TweiMenuVo> allMenu){
        List<TweiMenuVo> childList = new ArrayList<>();
        for (TweiMenuVo nav : allMenu) {
            if(nav.getMenuParentId().equals(id)){
                childList.add(nav);
            }
        }
        for (TweiMenuVo nav : childList) {
            nav.setSubMenu(getChild(nav.getMenuId(), allMenu));
        }

        if(childList.size() == 0){
            return new ArrayList<>();
        }
        return childList;
    }
}
