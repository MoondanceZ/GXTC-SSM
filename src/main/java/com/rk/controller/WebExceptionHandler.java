package com.rk.controller;

import com.rk.common.exception.GxtcException;
import com.rk.common.exception.DataNotFoundException;
import com.rk.dto.response.ReturnResult;
import com.rk.util.AjaxUtils;
import com.rk.util.ValidatorHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.UnexpectedTypeException;
import javax.ws.rs.NotFoundException;
import java.io.IOException;

/**
 * Created by Qin_Yikai on 2018-10-09.
 */
@ControllerAdvice()
public class WebExceptionHandler {
    private static final Logger logger = LogManager.getLogger(WebExceptionHandler.class);

    /**
     * @param exception UnexpectedTypeException
     * @return
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedTypeException.class)
    public void unexpectedType(UnexpectedTypeException exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("校验方法太多，不确定合适的校验方法。", exception);
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            AjaxUtils.writeJson(ReturnResult.Error("校验方法太多，不确定合适的校验方法"), response);
        } else
            response.sendError(500);
    }

    /**
     * 400 - Bad Request
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void messageNotReadable(HttpMessageNotReadableException exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("请求参数不匹配。", exception);
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            AjaxUtils.writeJson(ReturnResult.Error("请求参数不匹配"), response);
        } else
            response.sendError(500);
    }

    /**
     * 400 - Bad Request
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void methodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("请求参数不合法。", exception);
        Errors errors = exception.getBindingResult();
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            AjaxUtils.writeJson(ValidatorHelper.getErrorReturnResult(errors), response);
        } else
            response.sendError(500);
    }

    /**
     * 400 - Bad Request
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public void bindException(BindException exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("数据校验失败。", exception);
        Errors errors = exception.getBindingResult();
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            ReturnResult errorReturnResult = ValidatorHelper.getErrorReturnResult(errors);
            if (errorReturnResult.getMessage().contains("Failed to convert property value")){
                errorReturnResult.setMessage("请求参数不合法");
            }
            AjaxUtils.writeJson(errorReturnResult, response);
        } else
            response.sendError(500);
    }

    /**
     * 405 - Method Not Allowed
     */
    //@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("不支持当前请求方法", exception);
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            AjaxUtils.writeJson(ReturnResult.Error("不支持当前请求方法"), response);
        } else
            response.sendError(500);
    }

    /**
     * 415 - Unsupported Media Type
     */
    //@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public void handleHttpMediaTypeNotSupportedException(Exception exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("不支持当前媒体类型", exception);
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            AjaxUtils.writeJson(ReturnResult.Error("不支持当前媒体类型"), response);
        } else
            response.sendError(500);
    }

    /**
     * 文件上传失败
     */
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void maxUploadSizeExceeded(MaxUploadSizeExceededException exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("上传文件过大，上传失败", exception);
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            AjaxUtils.writeJson(ReturnResult.Error("上传文件过大，上传失败，不能超过5M"), response);
        } else
            response.sendError(500);
    }

    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void methodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("请求参数错误", exception);
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            AjaxUtils.writeJson(ReturnResult.Error("请求参数错误"), response);
        } else
            response.sendError(404);
    }

    @ExceptionHandler(GxtcException.class)
    public void gxtc(GxtcException exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("业务异常", exception);
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            AjaxUtils.writeJson(ReturnResult.Error(exception.getMessage()), response);
        } else
            response.sendError(500);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public void dataNotFound(DataNotFoundException exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("数据不存在", exception);
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            AjaxUtils.writeJson(ReturnResult.Error("数据不存在"), response);
        } else
            response.sendError(404);
    }

    @ExceptionHandler(NotFoundException.class)
    public void notFound(NotFoundException exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("HTTP 404 Not Found", exception);
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            AjaxUtils.writeJson(ReturnResult.Error("HTTP 404 Not Found"), response);
        } else
            response.sendError(404);
    }

    @ExceptionHandler(Exception.class)
    public void others(Exception exception, WebRequest webRequest, HttpServletResponse response) throws IOException {
        logger.error("内部异常", exception);
        if (AjaxUtils.isAjaxRequest(webRequest)) {
            AjaxUtils.writeJson(ReturnResult.Error("内部异常"), response);
        } else
            response.sendError(500);
    }
}
