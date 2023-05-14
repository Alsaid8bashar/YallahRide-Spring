package com.example.yallahride.Controller;

import com.example.yallahride.Service.Interface.SendGirdService;
import com.sendgrid.Response;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    SendGirdService sendGirdService;

    @PostMapping("/greeting")
    public ResponseEntity<HttpStatus> sendEmail(@RequestBody String email) throws IOException {
        sendGirdService.sendWelcomeEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
