package com.delta.auth.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname TweiRoleAuth
 * @Date 2019/4/8 09:10
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Data
@TableName("twei_role_auth")
public class TweiRoleAuth implements Serializable {
    @TableId( type= IdType.AUTO)
    private String raId;

    private String roleId;

    private String authId;

    private Integer dataVersion;
}
