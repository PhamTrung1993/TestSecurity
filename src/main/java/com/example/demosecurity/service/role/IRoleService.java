package com.example.demosecurity.service.role;

import com.example.demosecurity.entity.Role;
import com.example.demosecurity.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    String findRoleNameByUserId(Long id);
}
