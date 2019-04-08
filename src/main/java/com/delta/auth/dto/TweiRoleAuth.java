package com.delta.auth.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname TweiRoleAuth
 * @Date 2019/4/8 09:10
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Data
@TableName("twei_role_auth")
public class TweiRoleAuth implements Serializable {
    @TableId
    private String raId;

    private String roleId;

    private String roleName;

    private Integer roleLevel;

    private String roleParentId;

    private String roleDesc;

    private String authId;

    private String authName;

    private String authParentId;

    private String authDesc;

    private Integer authType;

    private Integer dataVersion;
}
