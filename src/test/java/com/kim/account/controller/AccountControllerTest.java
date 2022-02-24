package com.kim.account.controller;

import com.kim.account.entity.Account;
import com.kim.account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("회원 가입 화면 보이는지 테스트, 보여야함")
    void signUpForm() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andExpect(view().name("account/sign-up"));
    }

    @Test
    @DisplayName("올바른 회원 가입 폼이 전송되면 성공")
    void signUpFormValid() throws Exception{
        mockMvc.perform(post("/sign-up")
                        .param("nickname", "asd")
                        .param("email", "asd@gmail.com")
                        .param("password", "123456789")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
        ;
    }

    @Test
    @DisplayName("인증 메일 확인, 정상")
    @Transactional
    void checkEmailToken() throws Exception {
        Account account = Account.builder()
                .email("asd@email.com")
                .password("123456789")
                .nickname("kim")
                .build();
        Account newAccount = accountRepository.save(account);

        // 토큰 제너레이트한거 트랜잭션처리 해줘야함
        newAccount.generateEmailCheckToken();

        mockMvc.perform(get("/check-email-token")
                        .param("token", newAccount.getEmailCheckToken())
                        .param("email", newAccount.getEmail())
                )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("nickname"))
                .andExpect(view().name("account/checkedEmail"))
                .andExpect(authenticated().withUsername("kim"));
    }
}
