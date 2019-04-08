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
import com.delta.auth.dto.TweiUser;
import com.delta.common.utils.EncryptUtil;
import org.hamcrest.core.IsEqual;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

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
    public void before() {
        System.out.println("开始测试========>");
    }

    @After
    public void after() {
        System.out.println("结束测试========>");
    }

    @Test
    public void testInsert() {
        TweiUser user = new TweiUser();
        user.setUserid("weilizong");
        user.setUserName("韋利總");
        user.setPassword(EncryptUtil.getInstance().MD5("123456"));
        user.setEmail("weilizong2014@hotmail.com");
        user.setCreateAt(Calendar.getInstance().getTime());
        user.setOrganizationId("2");
        user.setDataVersion(0);
        Assert.assertThat(userService.insertOne(user).getMsg(), IsEqualIgnoringCase.equalToIgnoringCase("success"));
    }

    @Test
    public void testUpdate() {
        TweiUser user = new TweiUser();
        user.setUserid("weilizong");
        user.setUserName("韋利總");
        user.setPassword(EncryptUtil.getInstance().MD5("123456"));
        user.setEmail("weilizong2014@hotmail.com");
        user.setCreateAt(Calendar.getInstance().getTime());
        user.setOrganizationId("1");
        user.setDataVersion(0);
        Assert.assertThat(userService.updateOne(user).getMsg(), IsEqualIgnoringCase.equalToIgnoringCase("success"));
    }

    @Test
    public void testSelect() {
        TweiUser user = new TweiUser();
        user.setUserid("weilizong");
        Assert.assertThat(userService.selectOne(user).getUserid(), IsEqualIgnoringCase.equalToIgnoringCase("weilizong"));
    }

    @Test
    public void testList() {
        Assert.assertThat(userService.selectList().size(), IsEqual.equalTo(2));
    }

    @Test
    public void testDelete() {
        TweiUser user = new TweiUser();
        user.setUserid("weilizong2");
        user.setDataVersion(0);
        Assert.assertThat(userService.deleteOne(user).getMsg(), IsEqualIgnoringCase.equalToIgnoringCase("success"));
    }
}