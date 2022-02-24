package com.kim.account.service;

import com.kim.account.entity.Account;
import com.kim.account.dto.UserAccount;
import com.kim.account.form.AccountForm;
import com.kim.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    @Transactional
    public void completeSignup(Account account) {
        account.completeSignup();
        login(account);
    }

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

    public void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
