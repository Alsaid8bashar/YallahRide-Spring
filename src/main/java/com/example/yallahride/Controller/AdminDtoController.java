package com.example.yallahride.Controller;

import com.example.yallahride.Dto.AdminDTO;
import com.example.yallahride.Service.Interface.AdminDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin-dto")
public class AdminDtoController {

    @Autowired
    AdminDtoService adminDtoService;

    @GetMapping("/dashboard")
    public ResponseEntity<AdminDTO> getAdminDtoById() {
        return new ResponseEntity<>(adminDtoService.getAdminDto(), HttpStatus.OK);
    }

}
