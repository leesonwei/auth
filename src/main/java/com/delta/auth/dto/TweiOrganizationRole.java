package com.delta.auth.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname TweiOrganizationRole
 * @Date 2019/4/8 09:12
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Data
@TableName("twei_organization_role")
public class TweiOrganizationRole implements Serializable {
    @TableId
    private String or_id;

    private String roleId;

    private String roleName;

    private Integer roleLevel;

    private String roleParentId;

    private String roleDesc;
    private String organizationId;

    private String organizationName;

    private String organizationDesc;

    private String organizationParentId;

    private Integer organizationLevel;

    private Integer dataVersion;
}
