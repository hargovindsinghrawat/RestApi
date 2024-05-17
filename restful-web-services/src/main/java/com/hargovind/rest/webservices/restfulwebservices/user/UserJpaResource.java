package com.hargovind.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hargovind.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.hargovind.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	private UserRepository service;
	
	private PostRepository servicePost;

	public UserJpaResource(UserRepository service, PostRepository servicePost) {
		super();
		this.service = service;
		this.servicePost = servicePost;
	}

	@GetMapping(path = "/jpa/users")
	public List<User> retieveAllUsers() {
		return service.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retieveUser(@PathVariable int id) {
		Optional<User> user = service.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("id: "+ id); 
		}
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retieveAllUsers());
		entityModel.add(link.withRel("all-user"));
		
		return entityModel;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}
	
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retievePostForUser(@PathVariable int id) {
		Optional<User> user = service.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id: "+ id); 
		
		return user.get().getPosts();
	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id ,@Valid @RequestBody Post post) {
		
		Optional<User> user = service.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id: "+ id);
		
		post.setUser(user.get());
		
		Post savedPost = servicePost.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

}
