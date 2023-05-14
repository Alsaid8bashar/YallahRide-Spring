package com.example.yallahride.Service.Interface;

import com.sendgrid.Response;

import java.io.IOException;

public interface SendGirdService {


    Response sendWelcomeEmail(String email) throws IOException;
}
