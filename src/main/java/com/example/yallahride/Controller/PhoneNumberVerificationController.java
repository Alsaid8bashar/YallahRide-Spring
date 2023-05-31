package com.example.yallahride.Controller;

import com.example.yallahride.Config.Twillio.TwilioInitializer;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/phoneNumber")
@Slf4j
@CrossOrigin(value = "http://localhost:4200")
public class PhoneNumberVerificationController {
    @Autowired
    TwilioInitializer twilioInitializer;
    @Autowired
    Environment environment;
    @GetMapping(value = "/generateTOTP")
    public ResponseEntity<String> generateTOTP(@RequestParam("PhoneNumber") String phoneNumber) {
        Verification verification = Verification.creator(
                        environment.getProperty("service_sid"),
                        phoneNumber,
                        "sms")
                .create();

        return new ResponseEntity<>("Your TOTP has been sent to your verified phone number", HttpStatus.OK);
    }

    @GetMapping("/verifyTOTP/")
    public ResponseEntity<String> verifyUserTOTP
            (@RequestParam("code")String code, @RequestParam("phoneNumber") String phoneNumber) {

        try {
            VerificationCheck verificationCheck = VerificationCheck.creator(
                            environment.getProperty("service_sid"))
                    .setTo("+" + phoneNumber)
                    .setCode(code)
                    .create();

            return new ResponseEntity<>("OTP successfully verified" , HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Unverified OTP" ,HttpStatus.UNAUTHORIZED);
        }


    }
}
