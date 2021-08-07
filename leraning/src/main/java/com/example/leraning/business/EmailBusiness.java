package com.example.leraning.business;

import com.example.leraning.exception.BaseException;
import com.example.leraning.exception.EmailException;
import com.example.leraning.service.EmailService;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class EmailBusiness {
    private final EmailService emailService;

    public EmailBusiness(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendActivateUserEmail(String email,String name,String token) throws BaseException {

        String html;
        try{
            html = readEmailTemplate("email-activate-user");
        }catch(IOException e){
            throw EmailException.templateNotFound();
        }

        String subject = "";
        emailService.send(email,subject,html);

    }

    private String readEmailTemplate(String filename) throws IOException {
        File file = ResourceUtils.getFile("classpath:email/"+filename);
        return FileCopyUtils.copyToString(new FileReader(file));
    }
}
