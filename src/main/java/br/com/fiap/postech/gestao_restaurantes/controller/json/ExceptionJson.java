package br.com.fiap.postech.gestao_restaurantes.controller.json;

import br.com.fiap.postech.gestao_restaurantes.exception.SystemBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionJson {
    private final String code;
    private final String message;

    public ExceptionJson(final SystemBaseException baseException){
        this.code = baseException.getCode();
        this.message = baseException.getMessage();
    }

}
