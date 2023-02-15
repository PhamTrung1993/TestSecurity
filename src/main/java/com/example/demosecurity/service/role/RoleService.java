package com.example.demosecurity.service.role;

import com.example.demosecurity.entity.Role;
import com.example.demosecurity.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService implements IRoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public String findRoleNameByUserId(Long id) {
        return roleRepository.findRoleNameByUserId(id);
    }
}
