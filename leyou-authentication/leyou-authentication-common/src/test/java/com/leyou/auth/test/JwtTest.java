package com.leyou.auth.test;

import com.leyou.auth.entity.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * @Author: 98050
 * @Time: 2018-10-23 20:58
 * @Feature: JWT测试
 */
public class JwtTest {

    private static final String pubKeyPath = "D:\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "D:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU2MjgzMzQ1OX0.F5j7zhxMBRiRN-7dgWHZusEN5vNTrytVn1VcGzX7zfWRDmub2UeVbZA1rkoy6uivoEDZYzpkpJXbhp2eV6gQML48N_1V4-716bi9gYb6H893K44RZD-g7Y2iK8N9jM5e4YjronxJ4baxOrGEz-NbBTQ3weXUT1Cd1WfETbqs2bI";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }

    @Test
    public void date(){
        System.out.println(new Date());
    }
}