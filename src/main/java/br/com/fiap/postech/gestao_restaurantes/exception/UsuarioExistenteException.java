package br.com.fiap.postech.gestao_restaurantes.exception;

import lombok.Getter;

@Getter
public class UsuarioExistenteException extends SystemBaseException {

    private final String code = "usuario.usuarioJaExiste";
    private final String message = "Usuário já existe";
    private final Integer httpStatus = 422;
}
