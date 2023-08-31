package br.com.example.juspharus.Service;

import br.com.example.juspharus.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.example.juspharus.Dto.Request.ClienteRequestDTO;
import br.com.example.juspharus.Dto.Request.EnderecoRequestDto;
import br.com.example.juspharus.Dto.Response.ClienteResponseDTO;
import br.com.example.juspharus.Dto.Response.EnderecoResponseDTO;
import br.com.example.juspharus.Dto.Response.ResponseDTO;
import br.com.example.juspharus.entity.Cliente;
import br.com.example.juspharus.entity.Endereco;
import br.com.example.juspharus.repositories.ClienteRepository;



@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Transactional
    public ClienteResponseDTO salvar(ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = new Cliente();
        if(clienteRequestDTO.getEndereco() != null){
            cliente.setEndereco(converterEnderecoRequestEmEndereco(clienteRequestDTO.getEndereco()));
        }
        cliente = converteClienteDTORequestemCliente(clienteRequestDTO);
        repository.save(cliente);
        
        return new ClienteResponseDTO(cliente);
        
    }

    public ClienteResponseDTO getCliente(Long clienteId) throws Exception {
       Cliente cliente =  repository.findById(clienteId).orElseThrow(() -> new Exception("Digite uma id válida, cliente não encontrado"));
       return new ClienteResponseDTO(cliente);
    }

    public Page<ClienteResponseDTO> getAll(Integer pageNumber, Integer pageSize){
        Pageable pageable= PageRequest.of(pageNumber , pageSize , Sort.by("nome"));
        return repository.findAll(pageable).map(ClienteResponseDTO::new);
    }

    @Transactional
    public ClienteResponseDTO updateCliente(Long id, ClienteRequestDTO clienteRequestDTO) throws Exception{
        Cliente cliente =  repository.findById(id).orElseThrow(() -> new Exception("Digite uma id válida, cliente não encontrado"));
        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setTelefone(clienteRequestDTO.getTelefone());
        cliente.setTelefone2(clienteRequestDTO.getTelefone2());
        cliente.setCpf(clienteRequestDTO.getCpf());
        repository.save(cliente);
        
        return new ClienteResponseDTO(cliente);
    }

    @Transactional
    public EnderecoResponseDTO updateEndereco(Long id , EnderecoRequestDto enderecoRequestDto)throws Exception{
        Cliente cliente =  repository.findById(id).orElseThrow(() -> new Exception("Digite uma id válida, cliente não encontrado"));
        Endereco endereco = converterEnderecoRequestEmEndereco(enderecoRequestDto);
        cliente.setEndereco(endereco);
        repository.save(cliente);

        return new EnderecoResponseDTO(endereco);
    }


    public ResponseDTO deleteEndereco(Long id)throws Exception{
        Cliente cliente =  repository.findById(id).orElseThrow(() -> new Exception("Digite uma id válida, cliente não encontrado"));
        try {
            enderecoRepository.delete(cliente.getEndereco());
        }catch(Exception e ) {
            return  new ResponseDTO("Não foi possível excluir o Endereço pois cliente não possuí endereço cadastrado");
        }
        return new ResponseDTO("Endereço excluido com sucesso do cliente " + cliente.getNome());

    }

    @Transactional
    public ResponseDTO deleteCliente(Long id) throws Exception{
    
        repository.deleteById(id);
        
        return new ResponseDTO("Cliente excluido com sucesso");
    }

        public Cliente converteClienteDTORequestemCliente(ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setCpf(clienteRequestDTO.getCpf());
        cliente.setBirthdayDate(clienteRequestDTO.getBirthdayDate());
        cliente.setTelefone(clienteRequestDTO.getTelefone());
        cliente.setTelefone2(clienteRequestDTO.getTelefone2());
        return cliente;
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
