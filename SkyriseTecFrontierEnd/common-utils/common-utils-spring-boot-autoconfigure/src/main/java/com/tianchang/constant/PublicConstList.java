package com.tianchang.constant;


import java.util.List;
/**
 *公开端点列表常量
 * */
public class PublicConstList {
    //定义公开端点
    public static final List<String> PERMISSION_WHITE_LIST =  List.of(
            "/login",
            "/refresh",
            "/error",
            "/favicon.ico",
            "/logout"
    );

}
