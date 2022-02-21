package com.kim.account.validator;

import com.kim.account.entity.Account;
import com.kim.account.form.AccountForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountValidatorTest {

    @Autowired
    private AccountFormValidator validator;

    @Test
    @DisplayName("올바른 accountForm 입력시 에러가 없음")
    public void test1() {
        AccountForm accountForm = new AccountForm("kim", "asd@gmail.com", "123456789");
        Errors errors = new BeanPropertyBindingResult(new Account(), "validAccount");
        validator.validate(accountForm, errors);
        assertThat(errors.getErrorCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("짧은 닉네임 accountForm 입력시 에러가 있음")
    public void test2() {
        AccountForm accountForm = new AccountForm("ki", "asd@gmail.com", "123456789");
        Errors errors = new BeanPropertyBindingResult(new Account(), "invalidAccount");
        validator.validate(accountForm, errors);
        assertThat(errors.getErrorCount()).isEqualTo(1);
    }
}
