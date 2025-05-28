package br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.postech.gestao_restaurantes.domain.Endereco;
import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.exception.ErroAoAcessarRepositorioException;
import br.com.fiap.postech.gestao_restaurantes.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.entity.EnderecoEntity;
import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.entity.UsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.repository.EnderecoRepository;
import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioJpaGateway implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;

    private final EnderecoRepository enderecoRepository;

    @Override
    @Transactional
    public Long criar(Usuario usuario){
        try{
            UsuarioEntity usuarioEntity = mapToEntity(usuario);

            usuarioEntity.setDataUltimaAlteracao(LocalDateTime.now());

            enderecoRepository.save(usuarioEntity.getEndereco());

            return usuarioRepository.save(usuarioEntity).getId();

        }catch (Exception e){
            log.error(e.getMessage());
            throw new ErroAoAcessarRepositorioException();
        }
    }

    @Override
    @Transactional
    public void deletar(Long id) {
    	Optional<UsuarioEntity> usuarioById = usuarioRepository.findById(id);
    	
        if (!usuarioById.isPresent()) {
            throw new UsuarioNaoEncontradoException();
        }
        
        enderecoRepository.deleteById(usuarioById.get().getEndereco().getId());
        usuarioRepository.deleteById(id);
        
        log.info("Usuário deletado com sucesso: ID={}", id);
    }
    
    @Override
    public Optional<Usuario> buscarPorLogin(String login) {
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findByLogin(login);

        if(usuarioEntityOptional.isEmpty()){
            log.info("Usuário não foi encontrado: Login={}", login);
            return Optional.empty();
        }

        UsuarioEntity usuarioEntity = usuarioEntityOptional.get();

        Usuario usuario = mapToDomain(usuarioEntity);

        return Optional.of(usuario);
    }

    @Override
    @Transactional
	public void atualizar(Long id, Usuario usuario) {
    	try {
	        UsuarioEntity novoUsuario = mapToEntity(usuario);
	        novoUsuario.setId(id); 
	        novoUsuario.setDataUltimaAlteracao(LocalDateTime.now());
	        
	        enderecoRepository.save(novoUsuario.getEndereco());
	        usuarioRepository.save(novoUsuario);
	    }catch (Exception e){
	        log.error(e.getMessage());
	        throw new ErroAoAcessarRepositorioException();
	    }
	}

	@Override
	public void atualizarSenha(Long id, String novaSenha) {
		try {
			Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
	    	
	        if (!usuarioEntity.isPresent()) {
	            throw new UsuarioNaoEncontradoException();
	        }
	        
	        usuarioEntity.get().setDataUltimaAlteracao(LocalDateTime.now());
	        usuarioEntity.get().setSenha(novaSenha);
	        
	        usuarioRepository.save(usuarioEntity.get());    
	    }catch (Exception e){
	        log.error(e.getMessage());
	        throw new ErroAoAcessarRepositorioException();
	    }
	}
	
	@Override
	public Optional<Usuario> buscarPorId(Long id) {
		var usuarioEntity = usuarioRepository.findById(id)
				.orElseThrow(UsuarioNaoEncontradoException::new);
		
		var usuario = mapToDomain(usuarioEntity);
		return Optional.of(usuario);
	}
	
    private Usuario mapToDomain(UsuarioEntity usuarioEntity){
        Endereco endereco = new Endereco(
                usuarioEntity.getEndereco().getId(),
                usuarioEntity.getEndereco().getLogradouro(),
                usuarioEntity.getEndereco().getNumero(),
                usuarioEntity.getEndereco().getComplemento(),
                usuarioEntity.getEndereco().getBairro(),
                usuarioEntity.getEndereco().getCidade(),
                usuarioEntity.getEndereco().getEstado(),
                usuarioEntity.getEndereco().getCep()
        );

        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getCpf(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getLogin(),
                usuarioEntity.getSenha(),
                usuarioEntity.getDataUltimaAlteracao(),
                endereco
        );
    }

    private UsuarioEntity mapToEntity(Usuario usuario){

        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .id(usuario.getId())
                .cpf(usuario.getCpf())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .senha(usuario.getSenha())
                .build();

        EnderecoEntity enderecoEntity = EnderecoEntity.builder()
                .id(usuario.getEndereco().getId())
                .logradouro(usuario.getEndereco().getLogradouro())
                .numero(usuario.getEndereco().getNumero())
                .complemento(usuario.getEndereco().getComplemento())
                .bairro(usuario.getEndereco().getBairro())
                .cidade(usuario.getEndereco().getCidade())
                .estado(usuario.getEndereco().getEstado())
                .cep(usuario.getEndereco().getCep())
                .build();

        usuarioEntity.setEndereco(enderecoEntity);

        return usuarioEntity;
    }

	@Override
	public Optional<Usuario> buscarPorCpf(String cpf) {
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findByCpf(cpf);

        if(usuarioEntityOptional.isEmpty()){
            log.info("Usuário não foi encontrado: Login={}", cpf);
            return Optional.empty();
        }

        UsuarioEntity usuarioEntity = usuarioEntityOptional.get();

        Usuario usuario = mapToDomain(usuarioEntity);

        return Optional.of(usuario);	
      }
}
