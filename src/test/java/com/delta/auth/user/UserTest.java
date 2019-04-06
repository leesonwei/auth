/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserTest
 * Author:   anywhere
 * Date:     4/6 0006 16:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.delta.auth.user;

import com.delta.auth.AuthApplicationTests;
import com.delta.auth.Service.UserService;
import com.delta.auth.dao.UserMapper;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author anywhere
 * @create 4/6 0006
 * @since 1.0.0
 */
public class UserTest extends AuthApplicationTests {

    @Autowired
    private UserService userService;

    @Before
    public void before(){
        System.out.println("开始测试========>");
    }

    @After
    public void after(){
        System.out.println("结束测试========>");
    }

    @Test
    public void testListCount(){
        Assert.assertThat(userService.listCount(), IsEqual.equalTo(1));
    }
}