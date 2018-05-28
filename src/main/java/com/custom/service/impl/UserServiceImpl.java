package com.custom.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.custom.dao.UserDao;
import com.custom.entity.Role;
import com.custom.entity.User;
import com.custom.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(Objects.isNull(user))
			throw new UsernameNotFoundException("用户名【"+ username + "】不存在！");
		Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        Iterator<Role> iterator =  user.getList().iterator();
        while (iterator.hasNext()){
            collection.add(new SimpleGrantedAuthority(iterator.next().getRole_name()));
        }
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(),collection);
	}


	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}


	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}


	@Override
	public void save(User user) {
		userDao.save(user);
	}

}
