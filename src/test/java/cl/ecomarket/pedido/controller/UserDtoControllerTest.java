package cl.ecomarket.pedido.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import cl.ecomarket.pedido.dto.UserDto;
import cl.ecomarket.pedido.service.UserServiceDto;

@WebMvcTest(UserDtoController.class)
public class UserDtoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceDto userServiceDto;

    @Test
    public void testListarUsuarios() throws Exception {
        List<UserDto> mockUsers = List.of(new UserDto(1, "John Doe", "john@ejemplo.com"));
        when(userServiceDto.listarUsuarios()).thenReturn(mockUsers);

        mockMvc.perform(get("/api/v1/user/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(mockUsers.size()))
                .andExpect(jsonPath("$[0].id").value(mockUsers.get(0).getId()))
                .andExpect(jsonPath("$[0].name").value(mockUsers.get(0).getName()))
                .andExpect(jsonPath("$[0].email").value(mockUsers.get(0).getEmail()));
    }

    @Test
    public void testListarUsuariosDto() throws Exception {
        List<UserDto> mockUsers = List.of(new UserDto(2, "Jane Doe", "jane@ejemplo.com"));
        when(userServiceDto.listarUsuarios()).thenReturn(mockUsers);

        mockMvc.perform(get("/api/v1/user/listar/dto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(mockUsers.size()))
                .andExpect(jsonPath("$[0].id").value(mockUsers.get(0).getId()))
                .andExpect(jsonPath("$[0].name").value(mockUsers.get(0).getName()))
                .andExpect(jsonPath("$[0].email").value(mockUsers.get(0).getEmail()));
    }
}
