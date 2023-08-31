package br.com.example.juspharus.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.example.juspharus.Dto.Request.ClienteRequestDTO;
import br.com.example.juspharus.Dto.Response.ClienteResponseDTO;
import br.com.example.juspharus.Dto.Response.ResponseDTO;
import br.com.example.juspharus.entity.Cliente;
import br.com.example.juspharus.repositories.ClienteRepository;



@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    @Transactional
    public ClienteResponseDTO salvar(ClienteRequestDTO clienteResponseDTO){

        Cliente cliente = repository.save(converteClienteDTORequestemCliente(clienteResponseDTO));
        
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

    public Cliente converteClienteDTORequestemCliente(ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setCpf(clienteRequestDTO.getCpf());
        cliente.setBirthdayDate(clienteRequestDTO.getBirthdayDate());
        cliente.setTelefone(clienteRequestDTO.getTelefone());
        cliente.setTelefone2(clienteRequestDTO.getTelefone2());
        return cliente;
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
    public ResponseDTO deleteCliente(Long id) throws Exception{
    
        repository.deleteById(id);
        
        return new ResponseDTO("Cliente excluido com sucesso");
    }
}
