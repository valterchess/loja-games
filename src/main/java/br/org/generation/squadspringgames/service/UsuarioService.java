package br.org.generation.squadspringgames.service;

import br.org.generation.squadspringgames.model.Usuario;
import br.org.generation.squadspringgames.model.UsuarioLogin;
import br.org.generation.squadspringgames.repository.UsuarioRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> cadastrarUsuario(Usuario usuario){
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            return Optional.empty();
        if(!verificaIdade(usuario)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O Usuário é menor de idade!", null);
        }
        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        return Optional.of(usuarioRepository.save(usuario));
    }

    public Optional<Usuario> atualizarUsuario(Usuario usuario){
        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            return comparaUsuario(usuario);
        }
        return Optional.empty();
    }

    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin){
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
        if (usuario.isPresent()){
            if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())){
                String token = gerarBasicToken(usuario.get().getUsuario(), usuarioLogin.get().getSenha());
                usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setNome(usuario.get().getNome());
                usuarioLogin.get().setSenha(usuario.get().getSenha());
                usuarioLogin.get().setToken(token);
                return usuarioLogin;
            }
        }
        return Optional.empty();
    }

    /**
     * Inicio dos métodos privados
     */
    private String gerarBasicToken(String usuario, String senha){
        String tokenBase = usuario + ":" + senha;
        byte[] tokenBase64 = Base64.encodeBase64(tokenBase.getBytes(StandardCharsets.US_ASCII));
        return "Basic " + new String(tokenBase64);
    }

    private String criptografarSenha(String senha){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    private Optional<Usuario> comparaUsuario(Usuario usuario){
        var usuarioOp = usuarioRepository.findByUsuario(usuario.getUsuario());
        var isPresent = usuarioOp.isPresent();
        if (isPresent && usuario.getId() != usuarioOp.get().getId()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O Usuário já existe!", null);
        }
        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        return Optional.of(usuarioRepository.save(usuario));
    }


    private boolean compararSenhas(String senhaDigitda, String senhaDoBanco){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaDigitda,senhaDoBanco);
    }

    private boolean verificaIdade(Usuario usuario){
        return Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears() >= 18;
    }
}
