package com.gokul.contactmanager.config;

import com.gokul.contactmanager.dao.UserRepository;
import com.gokul.contactmanager.enities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.getUserByUserName(username);


        if(user==null){
            throw new UsernameNotFoundException("Could not find user");
        }

        CustomUserDetails customUserDetails=new CustomUserDetails(user);
        return customUserDetails;
    }
}
