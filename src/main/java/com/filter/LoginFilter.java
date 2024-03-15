package com.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import com.entity.pojo.User;
import com.entity.result.Result;
import com.entity.result.ResultCode;
import com.utils.AuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURL());
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response);
            return false;
        }
         User user = AuthUtil.getUser(token);
        if (user == null) {
            responseNoLoginInfo(response);
            return false;
        }
        ThreadLocalData.set(user); // 将用户信息存入threadLocal
        return true;
    }

    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result result = Result.resp(ResultCode.LOGIN_ERROR, "用户未登录");
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalData.remove();  // 移除threadLocal中的数据
    }
}
