package com.rk.util;

import com.rk.controller.WebExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Qin_Yikai on 2018-10-09.
 */
public class AjaxUtils {
    private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);


    /**
     * 验证是否是ajax请求
     *
     * @param webRequest
     * @return
     */
    public static boolean isAjaxRequest(WebRequest webRequest) {
        String requestedWith = webRequest.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equalsIgnoreCase(requestedWith) : false;
    }

    public static boolean isAjaxRequest(HttpServletRequest httpRequest) {
        String requestedWith = httpRequest.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equalsIgnoreCase(requestedWith) : false;
    }

    public static void writeJson(Object value, HttpServletResponse response) {

        try {
            /*JsonGenerator jsonGenerator = null;
            jsonGenerator = mapper.getFactory().createGenerator(response.getOutputStream(), JsonEncoding.UTF8);
            if (jsonGenerator != null) {
                jsonGenerator.writeObject(value);
            }*/

            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            String userJson = JsonUtils.ConvertObjectToJson(value);
            OutputStream out = response.getOutputStream();
            out.write(userJson.getBytes("UTF-8"));
            out.close();
        } catch (IOException e) {
            logger.error("writeJson error", e);
        }


    }

    private AjaxUtils() {
    }
}
