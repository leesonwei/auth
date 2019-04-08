package com.delta.auth.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname TweiOrganization
 * @Date 2019/4/8 08:57
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Data
@TableName("twei_organization")
public class TweiOrganization implements Serializable {
    @TableId
    private String organizationId;

    private String organizationName;

    private String organizationDesc;

    private String organizationParentId;

    private Integer organizationLevel;

    private String createBy;

    private Date createAt;

    private Integer dataVersion;
}
