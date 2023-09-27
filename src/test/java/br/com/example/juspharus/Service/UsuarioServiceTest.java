package br.com.example.juspharus.Service;

import br.com.example.juspharus.Dto.Request.UsuarioRequestDTO;
import br.com.example.juspharus.Dto.Response.EnderecoResponseDTO;
import br.com.example.juspharus.Dto.Response.UsuarioResponseDTO;
import br.com.example.juspharus.entity.Endereco;
import br.com.example.juspharus.entity.User;
import br.com.example.juspharus.entity.Usuario;
import br.com.example.juspharus.enums.UserRole;
import br.com.example.juspharus.repositories.EnderecoRepository;
import br.com.example.juspharus.repositories.UsuarioRepository;
import jakarta.validation.constraints.AssertTrue;
import lombok.With;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Spy
    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    EnderecoRepository enderecoRepository;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void salvarUsuarioComoAdmin() throws Exception {
        LocalDate localDate = LocalDate.of(2001, 9, 25);
        Date date = java.sql.Date.valueOf(localDate);
        //DTO
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO("Lucas", date,
                "293422", "9842-9732", null, null);
        //Admin
        User user = new User(2L,"Lucas","123456", UserRole.ADMIN,
                new Usuario(3L,"Lucas Teste", null , null , "67892", "98420-4040" , null , null));
        //Quando busca Autenticação , retorna:
        doReturn(user).when(usuarioService).buscaUsuarioPelaAutenticacao();
        //Quando save é chamado retorna Usuario
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario(1L, "Lucas", date, date,
                "293422", "9842-9732", null, new Endereco()));
        //Act
        UsuarioResponseDTO actual = usuarioService.salvar(usuarioRequestDTO);
        //Esperado
        UsuarioResponseDTO expected = new UsuarioResponseDTO(1L, "Lucas", date, date,
                "293422", "9842-9732", null, new EnderecoResponseDTO());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void salvarUsuarioComoClienteRetornaErro() throws Exception {

        //Mock User
        User user = new User(2L,"Lucas","123456", UserRole.CLIENTE,
                new Usuario(3L,"Lucas Teste", null , null , "67892", "98420-4040" , null , null));
        ////Quando busca Autenticação , retorna:
        doReturn(user).when(usuarioService).buscaUsuarioPelaAutenticacao();
        //Teste
        Exception exception = Assertions.assertThrows(Exception.class , () -> {
            usuarioService.salvar(any(UsuarioRequestDTO.class));
        });

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains("Perfil não tem autorização para criar novo Usuário"));

    }
        @Test
        void autorizaUsuarioMesmaIdAParalterarDados () throws Exception {

            User user = new User(2L,"Lucas","123456", UserRole.CLIENTE,
                    new Usuario(2L,"Lucas Teste", null , null , "67892", "98420-4040" , null , null));

            doReturn(user).when(usuarioService).buscaUsuarioPelaAutenticacao();

            boolean permissao = usuarioService.validaPermissaoUsuario(2L);
            Assertions.assertTrue(permissao);
        }

        @Test
        void negaUsuarioComIdDeAlterarDados () throws Exception {

        User user = new User(2L,"Lucas","123456", UserRole.CLIENTE,
                    new Usuario(3L,"Lucas Teste", null , null , "67892", "98420-4040" , null , null));

            doReturn(user).when(usuarioService).buscaUsuarioPelaAutenticacao();

            Exception exception = Assertions.assertThrows(Exception.class ,  () -> {
                usuarioService.validaPermissaoUsuario(2L);
            });
            String expectedMessage = "Usuário não autorizado";
            String testError = exception.getMessage();

            Assertions.assertTrue(testError.contains(expectedMessage));
        }

        @Test
        void autorizaAdminMesmoComIdDiferente() throws Exception {

            User user = new User(2L,"Lucas","123456", UserRole.ADMIN,
                    new Usuario(3L,"Lucas Teste", null , null , "67892", "98420-4040" , null , null));

            doReturn(user).when(usuarioService).buscaUsuarioPelaAutenticacao();

            boolean permissao = usuarioService.validaPermissaoUsuario(2L);
            Assertions.assertTrue(permissao);
        }
}