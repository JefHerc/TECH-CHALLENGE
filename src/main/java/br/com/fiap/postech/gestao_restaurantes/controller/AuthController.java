package br.com.fiap.postech.gestao_restaurantes.controller;

import br.com.fiap.postech.gestao_restaurantes.controller.json.UsuarioJson;
import br.com.fiap.postech.gestao_restaurantes.usecase.CriarUsuarioUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final CriarUsuarioUsecase criarUsuarioUsecase;

    @PostMapping("/register")
    public Long criar(@Valid @RequestBody UsuarioJson usuarioJson) {
        return criarUsuarioUsecase.criar(usuarioJson.mapToDomain());
    }

}
