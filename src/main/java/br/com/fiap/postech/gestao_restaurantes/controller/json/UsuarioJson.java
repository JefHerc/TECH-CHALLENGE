package br.com.fiap.postech.gestao_restaurantes.controller.json;


import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioJson {
    private Long id;

    @NotBlank
    private String cpf;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    private EnderecoJson endereco;

    public Usuario mapToDomain(){
        return new Usuario(
                id,
                cpf,
                nome,
                email,
                login,
                senha,
                LocalDateTime.now(),
                endereco.mapToDomain()
        );
    }
}
