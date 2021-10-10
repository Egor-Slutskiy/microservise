package com.example.producer.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardNumberValidator implements ConstraintValidator<CardNumber, Long> {

    @Override
    public boolean isValid(Long s, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))$");
        Matcher matcher = pattern.matcher(String.valueOf(s));
        return matcher.matches();
    }
}
