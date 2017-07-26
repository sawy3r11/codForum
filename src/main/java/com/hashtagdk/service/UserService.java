package com.hashtagdk.service;

import com.hashtagdk.model.Role;
import com.hashtagdk.model.User;
import com.hashtagdk.model.User_Role;
import com.hashtagdk.repository.RoleRepository;
import com.hashtagdk.repository.UserRepository;
import com.hashtagdk.repository.User_RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dawid on 7/17/17.
 */
@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private User_RoleRepository user_roleRepository;


    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, User_RoleRepository user_roleRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.user_roleRepository = user_roleRepository;
    }

    public void save(User user){
        user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
        //Role userRole
        Role userRole = roleRepository.findByRole("USER");

        User_Role user_role = new User_Role();
        user_role.setRole(userRole);
        user_role.setUsers(user);
        user_roleRepository.save(user_role);

        List<User_Role> user_roles = new ArrayList<User_Role>(0);
        user_roles.add(user_role);


    }

    public boolean checkIfExist(String login){
        return userRepository.findByLogin(login)!=null ? true:false;
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }


}
