package com.example.demosecurity.repo;

import com.example.demosecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query(nativeQuery = true,value = "select roles.rolename from roles join users on users.role_id = roles.id where users.id =:id ")
    String findRoleNameByUserId(@Param("id") Long id);
}
