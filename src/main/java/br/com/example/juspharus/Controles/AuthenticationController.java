package br.com.example.juspharus.Controles;

import br.com.example.juspharus.Dto.Request.AuthenticationRequestDto;
import br.com.example.juspharus.Dto.Request.RegisterRequestDTO;
import br.com.example.juspharus.Dto.Response.ResponseDTO;
import br.com.example.juspharus.Dto.Response.UsuarioResponseDTO;
import br.com.example.juspharus.Service.UsuarioService;
import br.com.example.juspharus.entity.User;
import br.com.example.juspharus.entity.Usuario;
import br.com.example.juspharus.repositories.UserRepository;
import br.com.example.juspharus.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequestDto authenticationRequestDto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationRequestDto.getLogin() , authenticationRequestDto.getPassword());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new ResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) throws Exception {
        if(repository.findByLogin(registerRequestDTO.getLogin()) != null) return ResponseEntity.badRequest().build();
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.salvar(registerRequestDTO.getUsuarioRequestDTO());
        Usuario usuario = usuarioService.getUsuarioERetornaEntidade(usuarioResponseDTO.getId());
        String encrpytedPassword = new BCryptPasswordEncoder().encode(registerRequestDTO.getPassword());
        User newUser = new User(registerRequestDTO.getLogin() , encrpytedPassword, registerRequestDTO.getRole() ,usuario);
        repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
