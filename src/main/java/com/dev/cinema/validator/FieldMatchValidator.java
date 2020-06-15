package com.dev.cinema.validator;

import com.dev.cinema.annotation.FieldsValueMatch;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.firstFieldName();
        secondFieldName = constraintAnnotation.secondFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        final Object firstValue = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
        final Object secondValue = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);
        return firstValue == null && secondValue == null
                || firstValue != null && firstValue.equals(secondValue);
    }
}
