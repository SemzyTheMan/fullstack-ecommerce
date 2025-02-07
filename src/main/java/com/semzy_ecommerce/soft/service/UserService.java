package com.semzy_ecommerce.soft.service;


import com.semzy_ecommerce.soft.dao.UserRepository;
import com.semzy_ecommerce.soft.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;


    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}