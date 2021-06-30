package com.github.jeff5m.ifood.cadastro.validations;

import com.github.jeff5m.ifood.cadastro.Restaurante;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueFieldValidator implements ConstraintValidator<Unique, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Restaurante possibleRestaurant = Restaurante.findByCnpj(value);
        return possibleRestaurant == null;
    }
}
