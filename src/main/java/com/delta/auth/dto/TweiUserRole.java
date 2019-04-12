package com.delta.auth.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname TweiUserRole
 * @Date 2019/4/8 09:05
 * @Author LIZONG.WEI
 * @Since 1.8
 */
@Data
@TableName("twei_user_role")
public class TweiUserRole implements Serializable {
    @TableId( type= IdType.AUTO)
    private String urId;

    private String userid;

    private String roleId;

    private Integer dataVersion;
}
