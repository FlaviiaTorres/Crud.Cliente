package com.portifolio.gitHub.cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portifolio.gitHub.cliente.models.Cliente;
import com.portifolio.gitHub.cliente.repository.ClienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class Controller {

	private ClienteRepository repository;
	
	@Autowired
	public Controller(ClienteRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return repository.save(cliente);
	}
	
	@GetMapping
	public List<Cliente> listar(){
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public Cliente ListarId(@PathVariable Integer id) {
		return repository
				.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarId(@PathVariable Integer id) {
		 repository
         .findById(id)
         .map( cliente -> {
             repository.delete(cliente);
             return Void.TYPE;
         })
         .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody  @Valid Cliente clienteAtualizado){
		repository
			.findById(id)
					.map(cliente ->{
						clienteAtualizado.setId(cliente.getId());
						return repository.save(clienteAtualizado);
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	
	
	
	
	
	
}
