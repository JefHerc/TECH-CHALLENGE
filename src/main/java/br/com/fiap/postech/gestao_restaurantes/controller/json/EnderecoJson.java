package br.com.fiap.postech.gestao_restaurantes.controller.json;

import br.com.fiap.postech.gestao_restaurantes.domain.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoJson {

    private Long id;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    private String cep;


    public Endereco mapToDomain(){
        return new Endereco(
          id,
          logradouro,
          numero,
          complemento,
          bairro,
          cidade,
          estado,
          cep
        );
    }
}
