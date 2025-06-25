package cl.ecomarket.pedido.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.test.web.servlet.MockMvc;

import cl.ecomarket.pedido.dto.UserDto;
import cl.ecomarket.pedido.service.UserServiceDto;
import cl.ecomarket.pedido.assemblers.UserDTOModelAssembler;

@WebMvcTest(UserDtoControllerV2.class)
public class UserDtoControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceDto userServiceDto;

    @MockBean
    private UserDTOModelAssembler userModelAssembler;

    @Test
    public void testListarUsuariosHateoas() throws Exception {
        UserDto user1 = new UserDto(1, "John Doe", "john@ejemplo.com");
        UserDto user2 = new UserDto(2, "Jane Doe", "jane@ejemplo.com");
        List<UserDto> mockUsers = List.of(user1, user2);

        when(userServiceDto.listarUsuarios()).thenReturn(mockUsers);

        // Mock assembler for each user
        Link listarLink = Link.of("/api/v1/user/listar").withRel("listar");
        Link listarDtoLink = Link.of("/api/v1/user/listar/dto").withRel("listarDto");
        EntityModel<UserDto> entityModel1 = EntityModel.of(user1, listarLink, listarDtoLink);
        EntityModel<UserDto> entityModel2 = EntityModel.of(user2, listarLink, listarDtoLink);

        when(userModelAssembler.toModel(user1)).thenReturn(entityModel1);
        when(userModelAssembler.toModel(user2)).thenReturn(entityModel2);

        mockMvc.perform(get("/api/v1/user/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.userDtoList").isArray())
                .andExpect(jsonPath("$._embedded.userDtoList[0].id").value(1))
                .andExpect(jsonPath("$._embedded.userDtoList[0].name").value("John Doe"))
                .andExpect(jsonPath("$._embedded.userDtoList[0].email").value("john@ejemplo.com"))
                .andExpect(jsonPath("$._embedded.userDtoList[0]._links.listar").exists())
                .andExpect(jsonPath("$._embedded.userDtoList[0]._links.listarDto").exists())
                .andExpect(jsonPath("$._embedded.userDtoList[1].id").value(2))
                .andExpect(jsonPath("$._embedded.userDtoList[1].name").value("Jane Doe"))
                .andExpect(jsonPath("$._embedded.userDtoList[1].email").value("jane@ejemplo.com"))
                .andExpect(jsonPath("$._embedded.userDtoList[1]._links.listar").exists())
                .andExpect(jsonPath("$._embedded.userDtoList[1]._links.listarDto").exists())
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    public void testListarUsuariosHateoasVacio() throws Exception {
        when(userServiceDto.listarUsuarios()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/user/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").doesNotExist())
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    public void testListarUsuariosDtoHateoas() throws Exception {
        UserDto user1 = new UserDto(1, "John Doe", "john@ejemplo.com");
        List<UserDto> mockUsers = List.of(user1);

        when(userServiceDto.listarUsuarios()).thenReturn(mockUsers);

        Link listarLink = Link.of("/api/v1/user/listar").withRel("listar");
        Link listarDtoLink = Link.of("/api/v1/user/listar/dto").withRel("listarDto");
        EntityModel<UserDto> entityModel1 = EntityModel.of(user1, listarLink, listarDtoLink);

        when(userModelAssembler.toModel(user1)).thenReturn(entityModel1);

        mockMvc.perform(get("/api/v1/user/listar/dto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.userDtoList").isArray())
                .andExpect(jsonPath("$._embedded.userDtoList[0].id").value(1))
                .andExpect(jsonPath("$._embedded.userDtoList[0]._links.listar").exists())
                .andExpect(jsonPath("$._embedded.userDtoList[0]._links.listarDto").exists())
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    public void testListarUsuariosDtoHateoasVacio() throws Exception {
        when(userServiceDto.listarUsuarios()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/user/listar/dto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").doesNotExist())
                .andExpect(jsonPath("$._links.self").exists());
    }
}