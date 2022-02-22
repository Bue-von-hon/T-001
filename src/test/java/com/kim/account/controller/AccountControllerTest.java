package com.kim.account.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
}
