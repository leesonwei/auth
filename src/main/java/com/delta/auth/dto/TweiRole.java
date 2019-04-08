package com.delta.auth.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname TweiRole
 * @Date 2019/4/8 08:42
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Data
@TableName("twei_role")
public class TweiRole implements Serializable {
    @TableId
    private String roleId;

    private String roleName;

    private Date createAt;

    private Date createBy;

    private Integer roleLevel;

    private String roleParentId;

    private String roleDesc;

    private Integer dataVersion;
}
