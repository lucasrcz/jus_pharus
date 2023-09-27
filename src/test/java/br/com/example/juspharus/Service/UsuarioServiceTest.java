package br.com.example.juspharus.Service;

import br.com.example.juspharus.entity.User;
import br.com.example.juspharus.entity.Usuario;
import br.com.example.juspharus.enums.UserRole;
import br.com.example.juspharus.repositories.EnderecoRepository;
import br.com.example.juspharus.repositories.UsuarioRepository;
import lombok.With;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    EnderecoRepository enderecoRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void salvar() {
    }

    @Test
    void primeiroSalvar() {
    }

    @Test
    void getUsuarioById() {
    }

    @Test
    void getUsuarioERetornaEntidade() {
    }

    @Test
    void getAll() {
    }

    @Test
    void updateCliente() {
    }

    @Test
    void updateEndereco() {
    }

    @Test
    void deleteEndereco() {
    }

    @Test
    void deleteCliente() {
    }

    @Test
    void converteClienteDTORequestemCliente() {
    }

    @Test
    void converterEnderecoRequestEmEndereco() {
    }

    @Test
    boolean buscaUsuarioPelaAutenticacao() {
        return false;
    }

        @Test
        void validaPermisaoUsuario() throws Exception {

            User user = new User(2L,"Lucas","123456", UserRole.CLIENTE,
                    new Usuario(2L,"Lucas Teste", null , null , "67892", "98420-4040" , null , null , UserRole.CLIENTE));
            when(SecurityContextHolder.getContext().getAuthentication()).thenReturn();
            when(securityContext.getAuthentication()).thenReturn(authentication);
            SecurityContextHolder.setContext(securityContext);

            boolean permissao = usuarioService.validaPermissaoUsuario(2L);
            Assertions.assertTrue(permissao);

        }
}