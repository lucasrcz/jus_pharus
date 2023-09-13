package br.com.example.juspharus.Controles;

import br.com.example.juspharus.Dto.Request.AuthenticationRequestDto;
import br.com.example.juspharus.Dto.Request.RegisterRequestDTO;
import br.com.example.juspharus.entity.User;
import br.com.example.juspharus.repositories.UserRepository;
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
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequestDto authenticationRequestDto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationRequestDto.getLogin() , authenticationRequestDto.getPassword());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO){
        if(repository.findByLogin(registerRequestDTO.getLogin()) != null) return ResponseEntity.badRequest().build();

        String encrpytedPassword = new BCryptPasswordEncoder().encode(registerRequestDTO.getPassword());
        User newUser = new User(registerRequestDTO.getLogin() , encrpytedPassword, registerRequestDTO.getRole());
        repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
