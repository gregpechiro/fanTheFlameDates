package com.cagnosolutions.cei.houseontherock.fantheflamedates.service;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.User;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.regex.Pattern;

@Transactional
@Service("userService")
public class UserService {

	@Autowired
	private UserRepository dao;
	
	public User insert(User user) {
		return dao.saveAndFlush(user);
	}

	public void update(User user){
		dao.save(user);
	}

    public void updateUser(String id, User updated) {
        User user = dao.findOne(id);
        user.setName(updated.getName());
        user.setEmail(updated.getEmail());
        user.setRole(updated.getRole());
        user.setActive(updated.getActive());
        dao.save(user);
    }

    public boolean updateUsername(String id, String username) {
        if(!dao.exists(username)) {
            User user = dao.findOne(id);
            User updated = new User(
                    username,
                        user.getPassword(),
                            user.getName(),
                                user.getEmail(),
                                    user.getRole(),
                                        user.getActive(),
											user.getChallengeProgress(),
												user.isChallengeComplete());
            dao.delete(id);
            dao.saveAndFlush(updated);
        }
        return (dao.exists(username) && !dao.exists(id));
    }

    public boolean usernameIsValid(String username) {
        return !Pattern.compile("[^A-Za-z0-9_]+").matcher(username).find();
    }

	public void delete(User user){
		dao.delete(user);
	}

    public User findById(String id) {
        return dao.findOne(id);
    }

	public List<User> findAll(){
		return dao.findAll();
	}

	public boolean exists(String username) {
		return dao.exists(username);
    }
	
	public List<User> findAllSorted(String sort, String order) {
		if ((isEmpty(sort) && isEmpty(order)) || isEmpty(sort)) {
			return dao.findAll();
		} 
		if (isEmpty(order) || !order.toLowerCase().startsWith("asc") || !order.toLowerCase().startsWith("desc")) {
			return dao.findAll(new Sort(Sort.Direction.fromString("ASC"), sort));
		}
		return dao.findAll(new Sort(Sort.Direction.fromString(order), sort));
	}
	
	private static boolean isEmpty(String string) {
		return (string == null || string.equals(""));
	}
}
