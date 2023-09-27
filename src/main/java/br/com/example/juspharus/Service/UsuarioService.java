package br.com.example.juspharus.Service;

import br.com.example.juspharus.entity.User;
import br.com.example.juspharus.enums.UserRole;
import br.com.example.juspharus.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.example.juspharus.Dto.Request.UsuarioRequestDTO;
import br.com.example.juspharus.Dto.Request.EnderecoRequestDto;
import br.com.example.juspharus.Dto.Response.UsuarioResponseDTO;
import br.com.example.juspharus.Dto.Response.EnderecoResponseDTO;
import br.com.example.juspharus.Dto.Response.ResponseDTO;
import br.com.example.juspharus.entity.Usuario;
import br.com.example.juspharus.entity.Endereco;
import br.com.example.juspharus.repositories.UsuarioRepository;

import java.util.Objects;


@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;



    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        if(buscaUsuarioPelaAutenticacao().getRole().getValue().equals("ADMIN")){
            Usuario usuario = converteClienteDTORequestemCliente(usuarioRequestDTO);
            Usuario saved = repository.save(usuario);
            return new UsuarioResponseDTO(saved);
        }else{
            throw new Exception("Perfil não tem autorização para criar novo Usuário");
        }
    }


    public UsuarioResponseDTO salvarRegistro(UsuarioRequestDTO usuarioRequestDTO) throws Exception {

        Usuario usuario = converteClienteDTORequestemCliente(usuarioRequestDTO);
        Usuario saved = repository.save(usuario);
        return new UsuarioResponseDTO(saved);
    }


    public UsuarioResponseDTO getUsuarioById(Long clienteId) throws Exception {
        validaPermissaoUsuario(clienteId);
        Usuario usuario =  repository.findById(clienteId).orElseThrow(() -> new Exception("Digite uma id válida, Usuario não encontrado"));
       return new UsuarioResponseDTO(usuario);
    }

    public Usuario getUsuarioERetornaEntidade(Long clienteId) throws Exception {
        return repository.findById(clienteId).orElseThrow(() -> new Exception("Digite uma id válida, Usuario não encontrado"));
    }

    public Page<UsuarioResponseDTO> getAll(Integer pageNumber, Integer pageSize) throws Exception {
        if(buscaUsuarioPelaAutenticacao().getAuthorities().contains(UserRole.ADVOGADO)||buscaUsuarioPelaAutenticacao().getAuthorities().contains(UserRole.ADMIN)){
        Pageable pageable= PageRequest.of(pageNumber , pageSize , Sort.by("nome"));
        return repository.findAll(pageable).map(UsuarioResponseDTO::new);
        }else{
            throw new Exception("Usuário não tem autorização");
        }
    }

    @Transactional
    public UsuarioResponseDTO updateCliente(Long id, UsuarioRequestDTO usuarioRequestDTO) throws Exception{
        validaPermissaoUsuario(id);
        Usuario usuario =  repository.findById(id).orElseThrow(() -> new Exception("Digite uma id válida, Usuario não encontrado"));
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setTelefone(usuarioRequestDTO.getTelefone());
        usuario.setTelefone2(usuarioRequestDTO.getTelefone2());
        usuario.setIdentificacao(usuarioRequestDTO.getIdentificacao());
        repository.save(usuario);
        
        return new UsuarioResponseDTO(usuario);
    }

    @Transactional
    public EnderecoResponseDTO updateEndereco(Long id , EnderecoRequestDto enderecoRequestDto)throws Exception{
        validaPermissaoUsuario(id);
        Usuario usuario =  repository.findById(id).orElseThrow(() -> new Exception("Digite uma id válida, Usuario não encontrado"));
        Endereco endereco = converterEnderecoRequestEmEndereco(enderecoRequestDto);
        usuario.setEndereco(endereco);
        repository.save(usuario);

        return new EnderecoResponseDTO(endereco);
    }


    public ResponseDTO deleteEndereco(Long id)throws Exception{
        validaPermissaoUsuario(id);
        Usuario usuario =  repository.findById(id).orElseThrow(() -> new Exception("Digite uma id válida, cliente não encontrado"));
        try {
            enderecoRepository.delete(usuario.getEndereco());
        }catch(Exception e ) {
            return  new ResponseDTO("Não foi possível excluir o Endereço pois o Usuario não possuí endereço cadastrado");
        }
        return new ResponseDTO("Endereço excluido com sucesso do Usuario:" + usuario.getNome());

    }

    @Transactional
    public ResponseDTO deleteCliente(Long id) throws Exception{
        validaPermissaoUsuario(id);
        repository.deleteById(id);
        return new ResponseDTO("Cliente excluido com sucesso");
    }

        public Usuario converteClienteDTORequestemCliente(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setIdentificacao(usuarioRequestDTO.getIdentificacao());
        usuario.setBirthdayDate(usuarioRequestDTO.getBirthdayDate());
        usuario.setTelefone(usuarioRequestDTO.getTelefone());
        usuario.setTelefone2(usuarioRequestDTO.getTelefone2());
        if(usuarioRequestDTO.getEndereco() == null){
                usuario.setEndereco(new Endereco());
        }else{
        usuario.setEndereco(converterEnderecoRequestEmEndereco(usuarioRequestDTO.getEndereco()));
        usuario.getEndereco().setUsuario(usuario);
        }

        return usuario;
    }

    public Endereco converterEnderecoRequestEmEndereco(EnderecoRequestDto enderecoRequestDto){
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoRequestDto.getRua());
        endereco.setNumero(enderecoRequestDto.getNumero());
        endereco.setComplemento(enderecoRequestDto.getComplemento());
        endereco.setCep(enderecoRequestDto.getCep());
        return endereco;

    }

    public User buscaUsuarioPelaAutenticacao() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()){
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }else{
            throw new Exception("Usuário não autenticado");
        }

    }
    public boolean validaPermissaoUsuario(Long id) throws Exception {
        User user = buscaUsuarioPelaAutenticacao();
        if(Objects.equals(user.getUsuario().getId(), id) || user.getRole().getValue().equals("ADMIN")) return true;
        else{
            throw new Exception("Usuário não autorizado");
        }
    }
}
