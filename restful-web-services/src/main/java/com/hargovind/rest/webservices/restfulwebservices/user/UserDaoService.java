package com.hargovind.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	
	private static Integer userCount = 0;
	
	static {
		users.add(new User(++userCount,"Hargovind",LocalDate.now().minusYears(30)));
		users.add(new User(++userCount,"Abhinav",LocalDate.now().minusYears(25)));
		users.add(new User(++userCount,"Dev",LocalDate.now().minusYears(35)));
		users.add(new User(++userCount,"Yogesh",LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findById(int id){
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		User user = users.stream().filter(predicate).findFirst().orElse(null);
		return user;
	}
	
	public User save(User user){
		user.setId(++userCount);
		users.add(user);
		return user;
	}
	
	public void DeleteById(int id){
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
}
