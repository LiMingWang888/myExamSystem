package com.wlm.exam.utils;

public class UserTypeUtil {
    public static String userType(Integer a) {
        if (a == 1) {
            return "学生";
        }
        if (a == 2) {
            return "管理员";
        }
        return null;
    }
}
