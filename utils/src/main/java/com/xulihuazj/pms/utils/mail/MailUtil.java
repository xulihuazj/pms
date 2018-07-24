package com.xulihuazj.pms.utils.mail;

import com.xulihuazj.pms.utils.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件发送 Util
 */
public class MailUtil {

    private final static Logger logger = LogManager.getLogger(MailUtil.class);

    /**
     * 邮件发送服务器
     */
    private static JavaMailSenderImpl javaMailSender;

    /**
     * 文本邮件内容
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     * @return
     * @Description：
     * @author: xulihua
     */
    public final static void sendSimpleMail(String from, String to, String subject, String content) throws Exception {
        logger.info("开始给发送邮件");
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(content);
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            LogHelper.exception(e, logger, "发送邮件异常，异常信息：{0}", e.getMessage());
            throw new RuntimeException(e);
        }
        LogHelper.info(logger, "发送邮件成功,邮件内容", content);
    }

    /**
     * Html邮件内容
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     * @return
     * @Description：发送HTML模板邮件
     * @author: xulihua
     */
    public final static void sendHTMLMail(String from, String to, String subject, String content) {
        logger.info("开始给发送通知邮件");
        try {
            MimeMessage htmlMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper msgHelper = new MimeMessageHelper(htmlMailMessage, "utf-8");
            msgHelper.setFrom(from);
            msgHelper.setTo(to);
            msgHelper.setSubject(subject);
            msgHelper.setText(content, true);
            javaMailSender.send(htmlMailMessage);
            LogHelper.info(logger, "发送邮件成功,邮件内容：{0}", content);
        } catch (Exception e) {
            LogHelper.exception(e, logger, "发送邮件异常,异常信息：");
            throw new RuntimeException(e);
        }
    }

    /**
     * @param host
     * @param port
     * @param protocol
     * @param username
     * @param password
     * @Description：初始化发送邮件服务器
     * @author: xulihua
     */
    public final static void init(String host, int port, String protocol, String username, String password) {
        try {
            javaMailSender = new JavaMailSenderImpl();
            Properties props = System.getProperties();
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            javaMailSender.setJavaMailProperties(props);
            javaMailSender.setHost(host);
            javaMailSender.setPort(port);
            javaMailSender.setProtocol(protocol.toLowerCase());
            javaMailSender.setUsername(username);
            javaMailSender.setPassword(password);
        } catch (Exception e) {
            LogHelper.error(logger, "初始化邮件服务器失败");
        }
        LogHelper.info(logger, "初始化邮件服务器成功");
    }

}