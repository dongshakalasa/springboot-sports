package com.ves.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class Email {
    @Autowired
    JavaMailSender javaMailSender;

    public Boolean sendSimpleMail(String code,String email) {
        try{
            // 构建一个邮件对象
            SimpleMailMessage message = new SimpleMailMessage();
            // 设置邮件主题
            message.setSubject("亿头牛注册验证码");
            // 设置邮件发送者，这个跟application.yml中设置的要一致
            message.setFrom("945405077@qq.com");
            // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
            // message.setTo("10*****16@qq.com","12****32*qq.com");
            message.setTo(email);
            //// 设置邮件抄送人，可以有多个抄送人
            //message.setCc("12****32*qq.com");
            //// 设置隐秘抄送人，可以有多个
            //message.setBcc("7******9@qq.com");
            // 设置邮件发送日期
            message.setSentDate(new Date());
            // 设置邮件的正文
            message.setText("验证码:"+code+",有效时间10分钟");
            // 发送邮件
            javaMailSender.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String getCode() {
        return String.format("%06d", System.currentTimeMillis() % 1000000L);
    }
}
