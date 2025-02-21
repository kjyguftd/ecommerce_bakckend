package com.example.user_service.filter;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SaTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 检查用户是否通过 Sa-Token 登录
        if (StpUtil.isLogin()) {
            // 获取当前登录用户的用户名
            String username = StpUtil.getLoginIdAsString();

            // 创建 Spring Security 的 Authentication 对象
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, new ArrayList<>()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 将 Authentication 对象设置到 SecurityContext 中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 继续执行过滤器链
        filterChain.doFilter(request, response);
    }
}