package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Account;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AccountControllerTest {
    @MockBean
    AccountService accountService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithMockUser
    public void testGetAccount() throws Exception {
        Account account = new Account("basharalsaid@gmail.com", "0797453540", "12131", new User());
        account.setId(1L);
        when(accountService.findAccountById(1L)).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("basharalsaid@gmail.com"));
    }


    @Test
    public void testSaveAccount() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        Account account = new Account("shnaqhassan@hotmail.com", "+962798084013", "$2a$12$URyEGDnS0up5B8mmANGkqu5i4yYCbE7p4B4lL8csL8cw8p1kTNFp2", user);

        when(accountService.saveAccount(account)).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.email").value("basharalsaid@gmail.com"));
                .andDo(print());
    }

    @Test
    public void testUpdateAccount() throws Exception {
        User user = new User("Hassan", "Al-Shannag", "image1", "Hi, I'm Hasan al Shannag!");
        Account account = new Account("shnaqhassan@hotmail.com", "+962798084013", "$2a$12$URyEGDnS0up5B8mmANGkqu5i4yYCbE7p4B4lL8csL8cw8p1kTNFp2", user);


        when(accountService.saveAccount(account)).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.put("/account/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.email").value("basharalsaid22@gmail.com"));
                .andDo(print());

    }

    @Test
    public void testDeleteAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testFindAccountByEmail() throws Exception {
        Account account = new Account("basharalsaid@gmail.com", "0797453540", "12131", new User());


        when(accountService.findAccountByEmail("basharalsaid@gmail.com")).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/find-by-email")
                        .content("basharalsaid@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("basharalsaid@gmail.com"));
    }

    @Test
    public void testFindAccountByPhoneNumber() throws Exception {
        Account account = new Account("basharalsaid@gmail.com", "0797453540", "12131", new User());

        when(accountService.findAccountByPhoneNumber("0797453540")).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/find-by-phone")
                        .content("0797453540")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("basharalsaid@gmail.com"));
    }

    @Test
    public void testFindAllAccounts() throws Exception {
        Account account1 = new Account("basharalsaid@gmail.com", "0797453540", "12131", new User());
        Account account2 = new Account("hsasnalshnnaq@gmail.com", "0797453540", "12131", new User());

        java.util.List<Account> accounts = Arrays.asList(account1, account2);

        when(accountService.findAllAccounts()).thenReturn(accounts);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].email").value("basharalsaid@gmail.com"))
                .andExpect(jsonPath("$[1].email").value("hsasnalshnnaq@gmail.com"));
    }

    @Test
    public void testDeleteAllAccounts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/delete-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetNumberOfAccounts() throws Exception {
        when(accountService.getNumberOfAccount()).thenReturn(10L);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(10));
    }


}
