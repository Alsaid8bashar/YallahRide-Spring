package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Role;
import com.example.yallahride.Service.Interface.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(name = "role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("save/role")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        return new ResponseEntity<Role>(roleService.saveRole(role), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Role>>findRoleById(@PathVariable Long id){
        return new ResponseEntity<Optional<Role>>(roleService.findRoleById(id) ,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>>findAllRoles(){
        return new ResponseEntity<List<Role>>(roleService.findAllRoles(),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Role>updateRole(@RequestBody Role role){
        return new ResponseEntity<Role>(roleService.updateRole(role),HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus>deleteAllRoles(){
        roleService.deleteAllRoles();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus>deleteRoleById(@PathVariable Long id){
        roleService.deleteRoleById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Long>getRolesCount(){
        return new ResponseEntity<Long>(roleService.getNumberOfRole(),HttpStatus.OK);
    }
}
