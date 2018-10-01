package com.rk.common.filter;

import com.rk.common.exception.GxtcException;
import com.rk.common.exception.NotFoundException;
import com.rk.controller.FileController;
import com.rk.dto.ReturnResult;
import com.rk.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Qin_Yikai on 2018-10-01.
 */
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean isError = false;
        boolean isNotFoundError = false;
        String errorMessage = null;
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (GxtcException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);

            isError = true;
            errorMessage = e.getMessage();
        } catch (NotFoundException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);

            isError = true;
            isNotFoundError = true;
            errorMessage = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);

            isError = true;
            errorMessage = "内部异常。";
        } finally {
            if (isError) {
                //如果是ajax请求响应头会有x-requested-with 
                if (httpServletRequest.getHeader("x-requested-with") != null
                        && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                    // 自定义异常的类，用户返回给客户端相应的JSON格式的信息
                    ReturnResult returnResult = ReturnResult.Error(errorMessage);

                    httpServletResponse.setContentType("application/json; charset=utf-8");
                    httpServletResponse.setCharacterEncoding("UTF-8");

                    String userJson = JsonUtil.ConvertObjectToJson(returnResult);
                    OutputStream out = httpServletResponse.getOutputStream();
                    out.write(userJson.getBytes("UTF-8"));
                    out.close();
                    out.flush();
                } else {
                    if (isNotFoundError)
                        httpServletResponse.sendRedirect("/404.jsp");
                    else
                        httpServletResponse.sendRedirect("/error.jsp");
                }
            }
        }
    }

}
