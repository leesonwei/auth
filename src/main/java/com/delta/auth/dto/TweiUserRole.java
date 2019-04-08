package com.delta.auth.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname TweiUserRole
 * @Date 2019/4/8 09:05
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Data
@TableName("twei_user_role")
public class TweiUserRole implements Serializable {
    @TableId
    private String urId;

    private String userid;

    private String userName;

    private String email;

    private Date lastLoginAt;

    private String organizationId;

    private String roleId;

    private String roleName;

    private Integer roleLevel;

    private String roleParentId;

    private String roleDesc;

    private Integer dataVersion;
}
