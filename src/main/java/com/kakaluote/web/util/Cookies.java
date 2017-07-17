package com.kakaluote.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dwb on 2017/7/13.
 */
public class Cookies {
    public static void clearCookie(HttpServletRequest request, HttpServletResponse response, String path) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            try{
                for(int i=0;i<cookies.length;i++) {
                    //System.out.println(cookies[i].getName() ":" cookies[i].getValue());
                    Cookie cookie = new Cookie(cookies[i].getName(), null);
                    cookie.setMaxAge(0);
                    cookie.setPath(path);//根据你创建cookie的路径进行填写
                    response.addCookie(cookie);
                }
            }catch(Exception ex) {
                ex.printStackTrace();
                System.out.println("删除Cookies发生异常！");
            }
        }
    }

    public static int getMemberId(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        int memberId = 0;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cook = cookies[i];
                if (cook.getName().equalsIgnoreCase("user")) { //获取键
                    memberId = Integer.parseInt(cook.getValue());    //获取值
                }
            }
        }
        return memberId;
    }
}
