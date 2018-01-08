package me.kyrene.springboot4integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBoot4IntegrationApplication.class)//这边的.class 不要测试的
public class SpringBoot4IntegrationApplicationTests {

    @Autowired(required=true) // 具体实现类用
    // @Resource//接口就用这个吧
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail() throws Exception {
        String from = "401442190@qq.com";
        String to = "sjtv512@163.com";
        String subject = "text";
        String context = "bbbbb";
    //    mailUtil.instance(from, to, subject, context);

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);//发送者.
//        message.setTo(to);//接收者.
//        message.setSubject(subject);//邮件主题.
//        message.setText(context);//邮件内容.
//        mailSender.send(message);//发送邮件
    }


}
