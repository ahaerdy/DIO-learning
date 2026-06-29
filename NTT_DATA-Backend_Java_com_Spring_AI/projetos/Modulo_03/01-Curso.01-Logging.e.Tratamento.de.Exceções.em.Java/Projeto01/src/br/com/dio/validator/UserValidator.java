package br.com.dio.validator;

import br.com.dio.exception.ValidatorException;
import br.com.dio.model.UserModel;

public class UserValidator {

    private UserValidator(){
    }

    public static void verifyModel(UserModel model) throws ValidatorException {
        if (stringIsBlank(model.getName())) {
            throw new ValidatorException("Informe um nome válido");
        }
        if (model.getName().length() <= 1) {
            throw new ValidatorException("O nome deve ter mais que 1 caractere");
        }
        if (stringIsBlank(model.getEmail())) {
            throw new ValidatorException("Informe um e-mail válido");
        }
        if (!model.getEmail().contains("@")) {
            throw new ValidatorException("Informe um email válido");
        }
    }

    private static boolean stringIsBlank(String value) {
        return value == null || value.isBlank();
    }


}
