package com.zhm.drug.util;

import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author kknever
 * @Date 2022/4/16
 **/

public class SessionUtil {
    public static String getUsernameFromSession(HttpSession session) {
        return (String)session.getAttribute("username");
    }
}
