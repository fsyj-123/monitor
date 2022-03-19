package site.fsyj.monitor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import site.fsyj.monitor.service.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author fsyj on 2022/3/19
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    /**
     * 使用@Value注入application.properties中指定的用户名
     */
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 用于发送文件
     */
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);

        mailSender.send(message);
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {

        log.info("发送HTML邮件开始：{},{},{}", to, subject, content);
        //使用MimeMessage，MIME协议
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        //MimeMessageHelper帮助我们设置更丰富的内容
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            log.info("发送HTML邮件成功");
        } catch (MessagingException e) {
            log.error("发送HTML邮件失败：", e);
        }
    }

    @Override
    public void sendAttachmentMail(String to, String subject, String content, String filePath) {

        log.info("发送带附件邮件开始：{},{},{},{}", to, subject, content, filePath);
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            //true代表支持多组件，如附件，图片等
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            //添加附件，可多次调用该方法添加多个附件
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            log.info("发送带附件邮件成功");
        } catch (MessagingException e) {
            log.error("发送带附件邮件失败", e);
        }


    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {

        log.info("发送带图片邮件开始：{},{},{},{},{}", to, subject, content, rscPath, rscId);
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            //重复使用添加多个图片
            helper.addInline(rscId, res);
            mailSender.send(message);
            log.info("发送带图片邮件成功");
        } catch (MessagingException e) {
            log.error("发送带图片邮件失败", e);
        }
    }
}
