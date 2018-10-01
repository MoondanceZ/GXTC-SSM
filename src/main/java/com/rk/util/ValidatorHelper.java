package com.rk.util;

import com.rk.dto.ReturnResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Qin_Yikai on 2018-10-01.
 */
public class ValidatorHelper {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static ReturnResult GetErrorReturnResult(Errors errors) {
        ReturnResult returnResult = null;

        if (errors.hasErrors()) {
            List<FieldError> fieldErrors = errors.getFieldErrors();
            StringBuilder sb = new StringBuilder();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            returnResult = ReturnResult.Error(sb.toString());
        }
        return returnResult;
    }

    public static <T> ReturnResult validate(T obj) {
        Map<String, StringBuffer> errorMap = null;
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() > 0) {
            errorMap = new HashMap<>();
            String property;
            for (ConstraintViolation<T> cv : set) {
                //这里循环获取错误信息，可以自定义格式
                property = cv.getPropertyPath().toString();
                if (errorMap.get(property) != null) {
                    errorMap.get(property).append(cv.getMessage());
                } else {
                    StringBuffer sb = new StringBuffer();
                    sb.append(cv.getMessage());
                    errorMap.put(property, sb);
                }
            }
        }
        if (errorMap != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, StringBuffer> error : errorMap.entrySet()) {
                sb.append(error.getValue());
            }
            return ReturnResult.Error(sb.toString());
        }
        return null;
    }
}
