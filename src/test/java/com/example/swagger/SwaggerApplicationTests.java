package com.example.swagger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SwaggerApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void contextLoads() {
        //简单的邮件发送
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("SpringBoot邮件任务测试");
        simpleMailMessage.setText("测试功能是否成功");
        simpleMailMessage.setTo("1033656550@qq.com");
        simpleMailMessage.setFrom("1033656550@qq.com");
        javaMailSender.send(simpleMailMessage);
    }

    @Test
    void contextLoads2() throws MessagingException {
        //相对复杂的邮件发送
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject("SpringBoot邮件任务测试复杂测试！");
        mimeMessageHelper.setText("<p style='color:red'>测试功能是否成功</p>",true);
        mimeMessageHelper.setTo("cuijiye1993@163.com");
        mimeMessageHelper.setFrom("1033656550@qq.com");

        mimeMessageHelper.addAttachment("工作环境参数.xlsx", new File("C:\\Users\\cuiji\\Desktop\\文件夹\\工作 相关\\工作环境参数.xlsx"));
        javaMailSender.send(mimeMessage);
    }
}
