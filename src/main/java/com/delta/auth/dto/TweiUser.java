/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: User
 * Author:   anywhere
 * Date:     3/18 0018 21:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.delta.auth.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author anywhere
 * @create 3/18 0018
 * @since 1.0.0
 */

@Data
@TableName("twei_user")
public class TweiUser implements Serializable {

    @TableId(type = IdType.UUID)
    private String userid;

    private String userName;

    private String password;

    private String email;

    private Date createAt;

    private Date lastLoginAt;

    private Date updateAt;

    private String organizationId;

    private Integer dataVersion;

    @TableField(exist = false)
    private TweiRole role;

    @Override
    public String toString() {
        return "TweiUser{" +
                "userid='" + userid + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", createAt=" + createAt +
                ", lastLoginAt=" + lastLoginAt +
                ", updateAt=" + updateAt +
                ", organizationId='" + organizationId + '\'' +
                ", dataVersion=" + dataVersion +
                '}';
    }
}