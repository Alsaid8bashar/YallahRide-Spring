package com.example.yallahride.Service.implementation;

import com.example.yallahride.Config.SendGrip.SendGridConstant;
import com.example.yallahride.Exceptions.EmailException;
import com.example.yallahride.Service.Interface.SendGirdService;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridServiceImpl implements SendGirdService {

    @Autowired
    SendGrid sendGrid;

    @Override
    public Response sendWelcomeEmail(String email) {
        Mail mail = new Mail();
        mail.setFrom(new Email(SendGridConstant.FROM_EMAIL));
        mail.setTemplateId(SendGridConstant.WELCOME_EMAIL_ID);
        Personalization personalization = new Personalization();
        personalization.addTo(new Email(email));
        mail.addPersonalization(personalization);
        return send(mail);
    }

    public Response send(Mail mail) {
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            return response;
        } catch (IOException ex) {
            throw new EmailException();
        }
    }

}
