
package com.marpe.cht.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marpe.cht.entities.User;
import com.marpe.cht.exceptions.DatabaseException;
import com.marpe.cht.exceptions.ExistingUserException;
import com.marpe.cht.repositories.UserRepository;
import com.marpe.cht.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
	}
	
//	public User insert(User obj) {
//		if(verifyExistingEmail(obj)) {
//			throw new ExistingUserException("Este email já está cadastrado.");
//		}
//		if(verifyExistingCpf(obj)) {
//			throw new ExistingUserException("Este CPF já está cadastrado.");
//		}
//		obj.setNome(convertToHumanCase(obj.getNome()));
//		obj.setPassword(encoder.encode(obj.getPassword()));
//		obj.setTelefone(formatarTelefone(obj.getTelefone()));
//		obj.setEmail(obj.getEmail().toLowerCase());
//		obj.setCpf(formatarPadraoCPF(obj.getCpf()));
//		obj.setAtivo(obj.getAtivo());
//		return repository.save(obj);
//	}
		
	public void delete(Long id) {
		try {
			repository.deleteById(id);	
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource not found with id: " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Resource not found with id: " + id);
		}
	}

	private void updateData(User entity, User obj) {
//		entity.setPassword(encoder.encode(obj.getPassword()));
//		entity.setNome(convertToHumanCase(obj.getNome()));
//		entity.setRg(obj.getRg());
//		entity.setEmail(obj.getEmail().toLowerCase());
//		entity.setCpf(formatarPadraoCPF(obj.getCpf()));
//		entity.setTelefone(formatarTelefone(obj.getTelefone()));
//		entity.setAtivo(obj.getAtivo());
//		entity.setPerfil(obj.getPerfil());
	}

//	private Boolean verifyExistingEmail(User obj) {
//		List<User> allUsers = findAll();
//		boolean result = allUsers.stream().anyMatch(x -> x.getEmail().equalsIgnoreCase(obj.getEmail()));
//		return result;
//	}
//	
//	private Boolean verifyExistingCpf(User obj) {
//		List<User> allUsers = findAll();
//		boolean result = allUsers.stream().anyMatch(x -> limparCaracteresCPF(x.getCpf()).equals(limparCaracteresCPF(obj.getCpf())));
//		return result;
//	}
//	
	private String limparCaracteresCPF(String cpf) {
		String cpfPuro = cpf.replaceAll("\\D", ""); //remover caracteres não-numéricos
		return cpfPuro;
	}
	
	public static String complementarTamanhoDoCPF(String cpf, Integer tamanho, Character caracter) {
	    if(cpf.length() < tamanho){
	        StringBuilder sb = new StringBuilder(cpf);
	        for(int cont = 0; cont < (tamanho-cpf.length()); cont ++){
	            sb.insert(0, caracter);
	        }
	        return sb.toString();
	    }
	    return cpf;
	}

	private String formatarPadraoCPF(String cpf) {
		String cpfPuro = complementarTamanhoDoCPF(limparCaracteresCPF(cpf), 11, '0');
		String cpfFormatado = cpfPuro.substring(0,3)+"."+cpfPuro.substring(3,6)+"."+cpfPuro.substring(6,9)+"-"+cpfPuro.substring(9,11);
		return cpfFormatado;
	}
	
	public static String limparCaracteresTelefone(String telefone) {
		String telefonePuro = telefone.replaceAll("\\D", ""); //remover caracteres não-numéricos
		return telefonePuro;
	}
	
	public static String formatarTelefone(String telefone) {
		String telefoneFormatado = limparCaracteresTelefone(telefone);
		
	    if(telefoneFormatado.length() == 10) {
	    	telefoneFormatado = "("+telefoneFormatado.substring(0,2)+") "+telefoneFormatado.substring(2,6)+"-"+telefoneFormatado.substring(6,10);
	    } else if(telefoneFormatado.length() == 11) {
	    	telefoneFormatado = "("+telefoneFormatado.substring(0,2)+") "+telefoneFormatado.substring(2,7)+"-"+telefoneFormatado.substring(7,11);
	    } else {
	    	return telefoneFormatado;
	    }
	    return telefoneFormatado;
	}
//	
//	public static String convertToHumanCase(String name) {
//	    StringBuilder sb = new StringBuilder();
//	    boolean firstLetter = true;
//	    name = name.toLowerCase();
//	    
//	    for (int i = 0; i < name.length(); i++) {
//	        char currentChar = name.charAt(i);
//	        if (currentChar == ' ') {
//	        	firstLetter = true;
//	        } else if (firstLetter) {
//	        	if (currentChar == name.charAt(0)) {
//		            sb.append(Character.toUpperCase(currentChar));
//		            firstLetter = false;
//	        	} else {
//		        	sb.append(" ");
//		            sb.append(Character.toUpperCase(currentChar));
//		            firstLetter = false;
//	        	}
//	        } else {
//	            if (Character.isUpperCase(currentChar)) {
//	                sb.append(' ');
//	            }
//	            sb.append(currentChar);
//	        }
//	    }
//	    return sb.toString();
//	}
//	
//	public User criaUserNulo() {
//		User user = new User();
//		user.setPassword("nulo");
//		user.setNome("Usuário removido");
//		user.setRg("nulo");
//		user.setEmail("nulo");
//		user.setCpf("00000000000");
//		user.setTelefone("00000000000");
//		user.setAtivo(false);
//		user.setPerfil(Perfil.COLABORADOR);
//		return user;
//	}
//	
//	
}
