package br.com.fiap.postech.gestao_restaurantes.exception;

import lombok.Getter;

@Getter
public class SenhaInvalidaException extends SystemBaseException {
	
	private static final long serialVersionUID = 1L;
	
	private final String code = "usuario.senhaIncorreta";
    private final String message = "A senha informada está incorreta!";
    private final Integer httpStatus = 401;
}
