package com.senqicloud.senqimediaserver.validator;

import com.senqicloud.senqimediaserver.model.request.UserRegisterRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// 确认密码校验器
public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, UserRegisterRequest> {

    @Override
    public boolean isValid(UserRegisterRequest value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.getPassword() != null && value.getPassword().equals(value.getConfirmPassword());
    }
}
