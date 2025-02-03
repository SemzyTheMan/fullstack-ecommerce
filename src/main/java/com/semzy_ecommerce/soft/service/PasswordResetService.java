package com.semzy_ecommerce.soft.service;

import com.semzy_ecommerce.soft.dao.PasswordResetRepository;
import com.semzy_ecommerce.soft.dao.UserRepository;
import com.semzy_ecommerce.soft.entity.PasswordResetToken;
import com.semzy_ecommerce.soft.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Value("${spring.mail.username}") private String sender;

    @Autowired
    private PasswordResetRepository passwordResetTokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void requestPasswordReset(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUser(user);
            resetToken.setExpiryDate(calculateExpiryDate());
            passwordResetTokenRepository.save(resetToken);

            sendPasswordResetEmail(user.getEmail(), token);
        }
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        if (resetToken != null && !isTokenExpired(resetToken)) {
            User user = resetToken.getUser();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            passwordResetTokenRepository.delete(resetToken);
        } else {
            throw new RuntimeException("Invalid or expired token");
        }
    }

    private Date calculateExpiryDate() {
        long expiryTimeInMillis = 24 * 60 * 60 * 1000; // 24 hours
        return new Date(System.currentTimeMillis() + expiryTimeInMillis);
    }

    private boolean isTokenExpired(PasswordResetToken token) {
        return token.getExpiryDate().before(new Date());
    }

    private void sendPasswordResetEmail(String email, String token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(email);
        mailMessage.setSubject("Password Reset Request");
        mailMessage.setText("To reset your password, click the link below:\n"
                + "http://localhost:8080/reset-password?token=" + token);
        mailSender.send(mailMessage);
    }
}
