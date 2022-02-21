package com.kim.account.validator;

import com.kim.account.form.AccountForm;
import com.kim.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AccountFormValidator implements Validator {

    private final AccountRepository accountRepository;
    @Override
    public boolean supports(Class<?> cls) {
        return cls.isAssignableFrom(AccountForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountForm accountForm = (AccountForm) target;

        if (accountRepository.existsByEmail(accountForm.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{accountForm.getEmail()}, "already used email");
        }

        if (accountRepository.existsByNickname(accountForm.getNickname())) {
            errors.rejectValue("Nickname", "invalid.Nickname", new Object[]{accountForm.getNickname()}, "already used Nickname");
        }
    }
}
