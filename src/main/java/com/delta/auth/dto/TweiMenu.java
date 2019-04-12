package com.delta.auth.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname TweiMenu
 * @Date 2019/4/9 15:12
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Data
@TableName("twei_menu")
public class TweiMenu implements Serializable {
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
}
