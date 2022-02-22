package com.kim.account.service;

import com.kim.account.entity.Account;
import com.kim.account.form.AccountForm;
import com.kim.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    private void sendConfirmEmail(Account newAccount) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(newAccount.getEmail());
        simpleMailMessage.setSubject("인증 메일");
        simpleMailMessage.setText("/check-email-token?token="+ newAccount.getEmailCheckToken()
                +"&email="+ newAccount.getEmail());

        javaMailSender.send(simpleMailMessage);
    }

    private Account saveAccount(@Valid AccountForm accountForm) {
        Account account = Account.builder()
                .nickname(accountForm.getNickname())
                .email(accountForm.getEmail())
                .password(passwordEncoder.encode(accountForm.getPassword()))
                .dramaTalkCreatedByWeb(true)
                .dramaTalkEnrollmentResultByWeb(true)
                .dramaTalkUpdatedByWeb(true)
                .build();
        return accountRepository.save(account);
    }

    @Transactional
    public Account process(AccountForm accountForm) {
        Account account = saveAccount(accountForm);
        account.generateEmailCheckToken();
        sendConfirmEmail(account);
        return account;
    }
}
