package com.kim.account.controller;

import com.kim.account.entity.Account;
import com.kim.account.repository.AccountRepository;
import com.kim.account.service.AccountService;
import com.kim.account.validator.AccountFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import com.kim.account.form.AccountForm;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountFormValidator accountFormValidator;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @InitBinder("AccountForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUpForm() {
        return "account/sign-up";
    }

    @PostMapping("/sign-up")
    public String submitAccountForm(@Valid AccountForm accountForm, Errors errors) {
        if (errors.hasErrors()) {
            return "account/sign-up";
        }
        accountService.process(accountForm);
        return "redirect:/";
    }

    @GetMapping("/check-email-token")
    public String checkEmailToken(String token, String email, Model model) {
        Account account = accountRepository.findByEmail(email);
        String view = "account/checkedEmail";

        if (account == null) {
            model.addAttribute("error", "wrong.email");
            return view;
        }

        if (!account.isValidToken(token)) {
            model.addAttribute("error", "wrong.token");
            return view;
        }

        accountService.completeSignup(account);
        model.addAttribute("nickname", account.getNickname());
        return view;
    }
}
