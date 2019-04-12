package com.delta.auth.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Classname TweiMenuVo
 * @Date 2019/4/9 16:25
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Data
@TableName("twei_menu")
public class TweiMenuVo implements Serializable {
    @TableId
    private String menuId;

    private String menuName;

    private String menuIcon;

    private Integer menuLevel;

    private Integer menuOrder;

    private String menuParentId;

    private String authId;

    private Integer dataVersion;

    private String createBy;

    private Date createAt;
    private String menuParentIdName;

    private String authIdUrl;

    @TableField(exist = false)
    private String active;

    private List<TweiMenuVo> subMenu;
}
