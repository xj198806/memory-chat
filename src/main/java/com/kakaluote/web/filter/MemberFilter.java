package com.kakaluote.web.filter;

import com.kakaluote.play.member.Member;
import com.kakaluote.play.support.PlayFacadeImpl;
import com.kakaluote.web.util.Cookies;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dwb on 2017/7/17.
 */
//@WebFilter(filterName = "memberFilter",urlPatterns = "/*")
public class MemberFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        int memberId = Cookies.getMemberId(request);
        if (memberId != 0){
            Member member = PlayFacadeImpl.getInstance().getMember(memberId);
            if (member == null){
                Cookies.clearCookie(request,response,"/");
                response.sendRedirect("/");
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
