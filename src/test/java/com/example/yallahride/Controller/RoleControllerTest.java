package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Role;
import com.example.yallahride.Service.Interface.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoleController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RoleControllerTest {
    @MockBean
    RoleService roleService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveRole() throws Exception {
        Role role = new Role( "Admin");
        role.setId(1L);
        when(roleService.saveRole(role)).thenReturn(role);
        mockMvc.perform(MockMvcRequestBuilders.post("/role/save")
                        .content(objectMapper.writeValueAsString(role))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.roleName").value("Admin"));
    }


    @Test
    public void testFindRoleById() throws Exception {
        Role role = new Role( "Admin");
        role.setId(1L);

        when(roleService.findRoleById(1L)).thenReturn(role);

        mockMvc.perform(MockMvcRequestBuilders.get("/role/find/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.roleName").value("Admin"));
    }

    @Test
    public void testFindAllRoles() throws Exception {
        java.util.List<Role> roles = new ArrayList<>();
        roles.add(new Role( "Admin"));
        roles.add(new Role( "User"));

        when(roleService.findAllRoles()).thenReturn(roles);

        mockMvc.perform(MockMvcRequestBuilders.get("/role/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roleName").value("Admin"))
                .andExpect(jsonPath("$[1].roleName").value("User"));
    }

    @Test
    public void testUpdateRole() throws Exception {
        Role role = new Role( "Updated Role");
        role.setId(1L);
        when(roleService.updateRole(role)).thenReturn(role);

        mockMvc.perform(MockMvcRequestBuilders.put("/role/update")
                        .content(objectMapper.writeValueAsString(role))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.roleName").value("Updated Role"));
    }

    @Test
    public void testDeleteAllRoles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/role/delete/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
