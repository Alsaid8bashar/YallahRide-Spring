package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.Role;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.RoleRepository;
import com.example.yallahride.Service.Interface.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    final private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleById(Long id) {
        return unwrapRole(roleRepository.findById(id),id);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteAllRoles() {
        roleRepository.deleteAll();
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public long getNumberOfRole() {
        return roleRepository.count();
    }

    static Role unwrapRole(Optional<Role> role, Long id) {
        if (role.isPresent()) return role.get();
        else throw new EntityNotFoundException(id, Ride.class);
    }
}
