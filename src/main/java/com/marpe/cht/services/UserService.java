
package com.marpe.cht.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marpe.cht.controllers.AuthController;
import com.marpe.cht.entities.Order;
import com.marpe.cht.entities.User;
import com.marpe.cht.entities.dtos.AuthRequest;
import com.marpe.cht.entities.enums.Datastate;
import com.marpe.cht.entities.enums.Role;
import com.marpe.cht.exceptions.DatabaseException;
import com.marpe.cht.exceptions.ExistingUserException;
import com.marpe.cht.repositories.UserRepository;
import com.marpe.cht.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

import com.marpe.cht.exceptions.ResourceNotFoundException;
import com.marpe.cht.exceptions.UnprocessableRequestException;

@Service
public class UserService implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Cacheable("users")
	public Page<User> findAll(PaginationRequest paginationRequest) {
		log.info("Executing service to findAll Users");
		PageRequest pageRequest = PageRequest.of(
            paginationRequest.getPage(),
            paginationRequest.getSize(),
            Sort.by(Sort.Direction.DESC, paginationRequest.getSortField()));
		Page<User> userPage = userRepository.findAll(pageRequest);
        return new PageImpl<User>(userPage.getContent(), userPage.getPageable(), userPage.getTotalElements());
	}
	
	public User findById(Long id) {
		log.info("Executing service to findById an User with param: id={}", id); 
		return userRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
	}
    
    @Transactional
	public User changePassword(Long id, User request) {
		log.info("Executing service to change password of User with params: id={} and user={}", id, request);
		try {
			User user = findById(id);
			user.setPassword(encoder.encode(request.getPassword()));
			return userRepository.save(user);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating User input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error updating User input data in database: " + e.getMessage());
		}
	}

    @Transactional
	public User changeRole(Long id, User request) {
		log.info("Executing service to change role of User with params: id={} and user={}", id, request);
		try {
			User user = findById(id);
			user.setRole(request.getRole());
			return userRepository.save(user);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating User input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error updating User input data in database: " + e.getMessage());
		}
	}
    
	@Transactional
	public String delete(Long id) {
		log.info("Executing service to delete an User with param: id={}", id);
		try {
			userRepository.deleteById(id);
			return "Registro removido com sucesso.";
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("User not found with id: " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	@Transactional
	public User registerUser(AuthRequest request) {
		log.info("Creating new user: " + request.getUsername());

		if(usernameAlreadyRegistered(request.getUsername()))
			throw new UnprocessableRequestException("This username is already registered.");
		
	    User user = new User();
	    user.setUsername(request.getUsername());
	    user.setPassword(encoder.encode(request.getPassword()));
	    user.setRole(Role.COLABORADOR);
	    user.setState(Datastate.ACTIVE);
	    userRepository.save(user);
	    return user;
	}
	
	private boolean usernameAlreadyRegistered(String username) {
		return userRepository.findByUsername(username).isPresent() ? true : false;
	}
	
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found."));
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
		


//	private void updateData(User entity, User obj) {
//		entity.setPassword(encoder.encode(obj.getPassword()));
//		entity.setNome(convertToHumanCase(obj.getNome()));
//		entity.setRg(obj.getRg());
//		entity.setEmail(obj.getEmail().toLowerCase());
//		entity.setCpf(formatarPadraoCPF(obj.getCpf()));
//		entity.setTelefone(formatarTelefone(obj.getTelefone()));
//		entity.setAtivo(obj.getAtivo());
//		entity.setPerfil(obj.getPerfil());
//	}

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
