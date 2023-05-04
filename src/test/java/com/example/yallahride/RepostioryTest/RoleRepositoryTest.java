package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.Role;
import com.example.yallahride.Repository.RoleRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepository;
    Role role;

    @BeforeAll
    public void setup() {
        role = roleRepository.save(new Role("Admin"));
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void testCreateRole() {
        Role role = roleRepository.save(new Role("User"));
        Assertions.assertTrue(role.getId() > 0);
    }


    @Test
    @Order(2)
    public void testFindRoleById() {
        Optional<Role> optionalRole = roleRepository.findById(role.getId());
        Role tempRole = optionalRole.get();
        Assertions.assertEquals(tempRole.getId(), role.getId());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testUpdateRole() {
        Optional<Role> optionalRole = roleRepository.findById(role.getId());
        role.setRollName("Driver");
        Role roleUpdated = roleRepository.save(role);
        Assertions.assertEquals(role.getRollName(), roleUpdated.getRollName());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void testFindAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        Assertions.assertTrue(roleList.size() > 0);
    }

    @Test
    @Order(5)
    public void testDeleteRoleByID() {
        roleRepository.deleteById(role.getId());
        Optional<Role> optionalRole = roleRepository.findById(role.getId());
        Assertions.assertTrue(!optionalRole.isPresent());
    }

    @Test
    @Order(6)
    public void testDeleteAllRoles() {
        roleRepository.deleteAll();
        Assertions.assertTrue(roleRepository.count() == 0);
    }
}
