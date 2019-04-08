package com.delta.auth.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname TweiAuth
 * @Date 2019/4/8 08:52
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Data
@TableName("twei_auth")
public class TweiAuth implements Serializable {

    @TableId
    private String authId;

    private String authName;

    private String authParentId;

    private String authDesc;

    private Integer authType;

    private String createBy;

    private Date createAt;

    private Integer dataVersion;
}
