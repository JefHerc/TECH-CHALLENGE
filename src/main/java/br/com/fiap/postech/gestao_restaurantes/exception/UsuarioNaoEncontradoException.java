package br.com.fiap.postech.gestao_restaurantes.exception;

import lombok.Getter;

@Getter
public class UsuarioNaoEncontradoException extends SystemBaseException {

	private static final long serialVersionUID = 1L;
	
	private final String code = "usuario.naoEncontrado";
    private final String message = "Usuário não encontrado!";
    private final Integer httpStatus = 404;
}
