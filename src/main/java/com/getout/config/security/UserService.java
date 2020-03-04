package com.getout.config.security;

import com.getout.user.User;
import com.getout.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new UsernameNotFoundException("Username not found "));
    }

    public UserDetails loadUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() ->
                new UsernameNotFoundException("ID not Found"));
    }
}
