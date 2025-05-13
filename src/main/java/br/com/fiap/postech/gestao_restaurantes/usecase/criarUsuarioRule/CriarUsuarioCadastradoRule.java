package br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule;

import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.exception.UsuarioExistenteException;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.OutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CriarUsuarioCadastradoRule implements CriarUsuarioRuleBase {

    private final UsuarioGateway usuarioGateway;

    @Override
    public Optional<OutputDto> validate(InputDto inputDto) {
        Usuario novoUsuario = inputDto.getNovoUsuario();

        Optional<Usuario> usuario = usuarioGateway.buscarPorCpf(novoUsuario.getCpf());

        if(usuario.isPresent()) {
            log.warn("Usuário já existe com o cpf informado. {}", novoUsuario.getCpf());
            throw new UsuarioExistenteException();
        }

        return Optional.empty();
    }
}
