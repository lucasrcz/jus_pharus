package br.com.example.juspharus.Service;

import br.com.example.juspharus.enums.PerfilEnum;
import br.com.example.juspharus.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.example.juspharus.Dto.Request.UsuarioRequestDTO;
import br.com.example.juspharus.Dto.Request.EnderecoRequestDto;
import br.com.example.juspharus.Dto.Response.ClienteResponseDTO;
import br.com.example.juspharus.Dto.Response.EnderecoResponseDTO;
import br.com.example.juspharus.Dto.Response.ResponseDTO;
import br.com.example.juspharus.entity.Usuario;
import br.com.example.juspharus.entity.Endereco;
import br.com.example.juspharus.repositories.UsuarioRepository;



@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Transactional
    public ClienteResponseDTO salvar(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = new Usuario();
        if(usuarioRequestDTO.getEndereco() != null){
            usuario.setEndereco(converterEnderecoRequestEmEndereco(usuarioRequestDTO.getEndereco()));
        }
        usuario = converteClienteDTORequestemCliente(usuarioRequestDTO);
        repository.save(usuario);
        
        return new ClienteResponseDTO(usuario);
        
    }


    public ClienteResponseDTO getUsuario(Long clienteId) throws Exception {
       Usuario usuario =  repository.findById(clienteId).orElseThrow(() -> new Exception("Digite uma id válida, Usuario não encontrado"));
       return new ClienteResponseDTO(usuario);
    }

    public Page<ClienteResponseDTO> getAll(Integer pageNumber, Integer pageSize){
        Pageable pageable= PageRequest.of(pageNumber , pageSize , Sort.by("nome"));
        return repository.findAll(pageable).map(ClienteResponseDTO::new);
    }

    @Transactional
    public ClienteResponseDTO updateCliente(Long id, UsuarioRequestDTO usuarioRequestDTO) throws Exception{
        Usuario usuario =  repository.findById(id).orElseThrow(() -> new Exception("Digite uma id válida, Usuario não encontrado"));
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setTelefone(usuarioRequestDTO.getTelefone());
        usuario.setTelefone2(usuarioRequestDTO.getTelefone2());
        usuario.setIdentificacao(usuarioRequestDTO.getIdentificacao());
        repository.save(usuario);
        
        return new ClienteResponseDTO(usuario);
    }

    @Transactional
    public EnderecoResponseDTO updateEndereco(Long id , EnderecoRequestDto enderecoRequestDto)throws Exception{
        Usuario usuario =  repository.findById(id).orElseThrow(() -> new Exception("Digite uma id válida, Usuario não encontrado"));
        Endereco endereco = converterEnderecoRequestEmEndereco(enderecoRequestDto);
        usuario.setEndereco(endereco);
        repository.save(usuario);

        return new EnderecoResponseDTO(endereco);
    }


    public ResponseDTO deleteEndereco(Long id)throws Exception{
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
        usuario.setPerfilEnum(PerfilEnum.valueOf(usuarioRequestDTO.getPerfilEnum()));
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
}
