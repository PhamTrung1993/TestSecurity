package com.example.demosecurity.service.User;

import com.example.demosecurity.entity.User;
import com.example.demosecurity.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);
}
