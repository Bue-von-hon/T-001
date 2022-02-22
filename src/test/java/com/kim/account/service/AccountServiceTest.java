package com.kim.account.service;

import com.kim.account.entity.Account;
import com.kim.account.form.AccountForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    @DisplayName("올바른 계정 입력시 정상 저장, 이때 패스워드는 인코딩되어야 한다")
    public void saveAccount() {
        AccountForm accountForm = new AccountForm("asd", "asd@gmail.com", "123456789");
        Account account2 = accountService.process(accountForm);
        assertThat(account2.getPassword()).isNotEqualTo("123456789");
        assertThat(account2.getEmailCheckToken()).isNotNull();
    }
}
