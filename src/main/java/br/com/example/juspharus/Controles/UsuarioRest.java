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

import br.com.example.juspharus.Dto.Request.UsuarioRequestDTO;
import br.com.example.juspharus.Dto.Response.ClienteResponseDTO;
import br.com.example.juspharus.Dto.Response.ResponseDTO;
import br.com.example.juspharus.Service.UsuarioService;


@RestController
@RequestMapping(value = "/usuario")

public class UsuarioRest {
    
    @Autowired
    UsuarioService service;
    
    @PostMapping
    @Operation(summary = "Cria um Usuario com perfil de cliente no banco de dados")
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody @Validated UsuarioRequestDTO usuarioRequestDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(usuarioRequestDTO));
    }

    @GetMapping
    @Operation(summary = "Busca um Usuario a partir de uma ID válida")
    public ResponseEntity<ClienteResponseDTO> getUsuario(@RequestParam Long clienteId) throws Exception {
        return ResponseEntity.ok(service.getUsuario(clienteId));
    }

    @GetMapping(value = "/pagination")
    @Operation(summary = "Mostra uma paginação de todos os Usuarios no Banco de Dados, ordenado por ordem alfabetica. Aceita parâmetros para controlar a paginação")
    public Page<ClienteResponseDTO> getAll(@RequestParam(defaultValue = "0") Integer pageNumber , @RequestParam(defaultValue = "10") Integer pagesize){
        return service.getAll(pageNumber , pagesize);
    }

    @PutMapping
    @Operation(summary = "Atualiza um usuario no banco de dados")
    public ResponseEntity<ClienteResponseDTO> updateCliente(@RequestBody @Validated UsuarioRequestDTO usuarioRequestDTO, @RequestParam Long id ) throws Exception{
        return ResponseEntity.ok(service.updateCliente(id, usuarioRequestDTO));
    }

    @DeleteMapping
    @Operation(summary = "Deleta Usuario baseado em uma ID")
    public ResponseEntity<ResponseDTO> deleteCliente(@RequestParam Long id) throws Exception{
        return ResponseEntity.ok(service.deleteCliente(id));
    }

    @PutMapping(value = "/endereco")
    @Operation(summary = "Cria um novo endereço relacionado a um cliente especifico")
    public ResponseEntity<EnderecoResponseDTO> updateEndereco(@RequestBody @Validated EnderecoRequestDto enderecoRequestDto , @RequestParam Long id) throws Exception{
        return ResponseEntity.ok(service.updateEndereco(id, enderecoRequestDto));
    }

    @DeleteMapping(value = "/endereco/delete")
    @Operation(summary = "Delete um endereço de um usuario específico")
    public ResponseEntity<ResponseDTO> deleteEndereco(@RequestParam Long id)throws Exception{
        return ResponseEntity.ok(service.deleteEndereco(id));
    }
}
