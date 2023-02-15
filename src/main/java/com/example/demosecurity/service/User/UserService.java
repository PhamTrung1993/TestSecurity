package com.example.demosecurity.service.User;

import com.example.demosecurity.entity.User;
import com.example.demosecurity.entity.UserPrinciple;
import com.example.demosecurity.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()){
            return UserPrinciple.build(userOptional.get());
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        return user;
    }
}
