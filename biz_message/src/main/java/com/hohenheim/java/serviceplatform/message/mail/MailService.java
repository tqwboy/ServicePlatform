package com.hohenheim.java.serviceplatform.message.mail;

import com.hohenheim.java.serviceplatform.message.mail.params.SimpleMailParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Hohenheim
 * @date 2022/7/3
 * @description 邮件发送服务
 */
@Service
@Slf4j
public class MailService {
    @Autowired
    private JavaMailSender mMailSender;

    /**
     * 发送简单邮件
     * @param mailParams 邮件
     */
    public boolean sendSimpleMail(SimpleMailParams mailParams) {
        boolean sendResult = true;
        SimpleMailMessage sendMessage = new SimpleMailMessage();

        try {
            sendMessage.setFrom(mailParams.getFrom());
            sendMessage.setTo(mailParams.getTo());
            sendMessage.setReplyTo(mailParams.getReplayTo());
            sendMessage.setCc(mailParams.getCc());
            sendMessage.setBcc(mailParams.getBcc());
            sendMessage.setSubject(mailParams.getTitle());
            sendMessage.setText(mailParams.getContent());
            sendMessage.setSentDate(new Date());

            mMailSender.send(sendMessage);
        }
        catch (MailException e) {
            sendResult = false;
            log.error("[SendMail] 邮件发送失败", e);
        }

        return sendResult;
    }
}