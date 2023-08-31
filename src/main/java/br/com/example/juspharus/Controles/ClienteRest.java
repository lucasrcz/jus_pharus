package br.com.example.juspharus.Controles;

import br.com.example.juspharus.Dto.Request.EnderecoRequestDto;
import br.com.example.juspharus.Dto.Response.EnderecoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.com.example.juspharus.Dto.Request.ClienteRequestDTO;
import br.com.example.juspharus.Dto.Response.ClienteResponseDTO;
import br.com.example.juspharus.Dto.Response.ResponseDTO;
import br.com.example.juspharus.Service.ClienteService;


@RestController
@RequestMapping(value = "/cliente")

public class ClienteRest {
    
    @Autowired
    ClienteService service;
    
    @PostMapping
    @Operation(summary = "Cria um novo cliente no banco de dados")
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody @Validated ClienteRequestDTO clienteRequestDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(clienteRequestDTO));
    }

    @GetMapping
    @Operation(summary = "Busca um cliente a partir de uma ID válida")
    public ResponseEntity<ClienteResponseDTO> getCliente(@RequestParam Long clienteId) throws Exception {
        return ResponseEntity.ok(service.getCliente(clienteId));
    }

    @GetMapping(value = "/pagination")
    @Operation(summary = "Mostra uma paginação de todos os clientes no Banco de Dados, ordenado por ordem alfabetica. Aceita parâmetros para controlar a paginação")
    public Page<ClienteResponseDTO> getAll(@RequestParam(defaultValue = "0") Integer pageNumber , @RequestParam(defaultValue = "10") Integer pagesize){
        return service.getAll(pageNumber , pagesize);
    }

    @PutMapping
    @Operation(summary = "Atualiza um cliente no banco de dados")
    public ResponseEntity<ClienteResponseDTO> updateCliente(@RequestBody @Validated ClienteRequestDTO clienteRequestDTO, @RequestParam Long id ) throws Exception{
        return ResponseEntity.ok(service.updateCliente(id, clienteRequestDTO));
    }

    @DeleteMapping
    @Operation(summary = "Deleta cliente baseado em uma ID")
    public ResponseEntity<ResponseDTO> deleteCliente(@RequestParam Long id) throws Exception{
        return ResponseEntity.ok(service.deleteCliente(id));
    }

    @PutMapping(value = "/endereco")
    @Operation(summary = "Cria um novo endereço")
    public ResponseEntity<EnderecoResponseDTO> updateEndereco(@RequestBody @Validated EnderecoRequestDto enderecoRequestDto , @RequestParam Long id) throws Exception{
        return ResponseEntity.ok(service.updateEndereco(id, enderecoRequestDto));
    }

    @DeleteMapping(value = "/endereco/delete")
    @Operation(summary = "Delete um endereço de um cliente específico")
    public ResponseEntity<ResponseDTO> deleteEndereco(@RequestParam Long id)throws Exception{
        return ResponseEntity.ok(service.deleteEndereco(id));
    }
}
