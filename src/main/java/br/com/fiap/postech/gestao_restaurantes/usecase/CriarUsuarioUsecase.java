package br.com.fiap.postech.gestao_restaurantes.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.CriarUsuarioRuleBase;
import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.InputDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriarUsuarioUsecase {

    private final UsuarioGateway usuarioGateway;
    private final List<CriarUsuarioRuleBase> rules;

    public Long criar(Usuario usuario) {
        validaRegras(usuario);

        return usuarioGateway.criar(usuario);
    }

    private void validaRegras(Usuario usuario) {
        var inputDto = new InputDto(usuario);
        rules.forEach(rule -> rule.validate(inputDto));
    }


}
