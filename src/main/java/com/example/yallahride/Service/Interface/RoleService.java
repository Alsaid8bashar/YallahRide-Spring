package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role saveRole(Role role);

    Role findRoleById(Long id);

    List<Role> findAllRoles();
    Role updateRole(Role role);

    void deleteAllRoles();

    void deleteRoleById(Long id);

    long getNumberOfRole();
    
}
