package com.rk.common.validation.Impl;

import com.rk.common.validation.MaxLength;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Qin_Yikai on 2018-10-01.
 */
@Service
public class MaxLengthImpl implements ConstraintValidator<MaxLength, String> {
    private int value;

    @Override
    public void initialize(MaxLength constraintAnnotation) {
        //传入value 值，可以在校验中使用
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext context) {
        if (str == null || str.trim().length() == 0)
            return true;
        else if (str.length() >= value)
            return false;
        return true;
    }
}
