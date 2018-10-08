package com.rk.common.filter;

import com.rk.common.exception.GxtcException;
import com.rk.common.exception.DataNotFoundException;
import com.rk.controller.FileController;
import com.rk.dto.ReturnResult;
import com.rk.util.AjaxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        } catch (DataNotFoundException e) {
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
                if (AjaxUtils.isAjaxRequest(httpServletRequest)) {
                    AjaxUtils.writeJson(ReturnResult.Error(errorMessage), httpServletResponse);
                } else {
                    if (isNotFoundError)
                        httpServletResponse.sendError(404);
                    else
                        httpServletResponse.sendError(500);
                }
            }
        }
    }

}
