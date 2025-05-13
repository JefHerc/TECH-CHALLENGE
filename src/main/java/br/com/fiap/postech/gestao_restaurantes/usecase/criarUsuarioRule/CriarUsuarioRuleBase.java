package br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule;

import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.OutputDto;

import java.util.Optional;

public interface CriarUsuarioRuleBase {

    Optional<OutputDto> validate(InputDto inputDto);

}
