package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void saveRole(Role role);

    Optional<Role> findRoleById(Long id);

    List<Role> findAllRoles();

    void deleteAllRoles();

    void deleteRoleById(Long id);

    long getNumberOfRole();
}
