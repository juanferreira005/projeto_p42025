package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.Autenticacao.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.remote.JMXAuthenticator;

@RestController
@RequestMapping(path = "/login")
public class AuthenticacaoController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity efetuarlogin
            (@RequestBody @Valid DadosAutenticacao dadosAutenticacao){
        var authenticationtoken =
                new UsernamePasswordAuthenticationToken
                (dadosAutenticacao.login(), dadosAutenticacao.senha());
        System.out.println(dadosAutenticacao.senha());
        var authentication =
        manager.authenticate(authenticationToken);
        var tokenJWT =
                tokenService.gerarToken((Usuario)
                authentication.getPrincipal());
        return ResponseEntity.ok(new
                DadosTokenJWT(tokenJWT));

    }
}
