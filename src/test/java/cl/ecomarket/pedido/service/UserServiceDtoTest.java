package cl.ecomarket.pedido.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.ecomarket.pedido.client.UserFeingInterfaz;
import cl.ecomarket.pedido.dto.UserDto;

@ExtendWith(MockitoExtension.class)
// Esta anotación indica que estamos utilizando Mockito para las pruebas unitarias
// y que queremos extender la funcionalidad de JUnit con las capacidades de Mockito.
public class UserServiceDtoTest {

    @Mock
    private UserFeingInterfaz userFeingInterfaz;

    @InjectMocks
    private UserServiceDto userServiceDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarUsuarios() {
        // Given
        UserDto user1 = new UserDto();
        UserDto user2 = new UserDto();
        List<UserDto> mockUsers = Arrays.asList(user1, user2);

        when(userFeingInterfaz.listarUsuarios()).thenReturn(mockUsers);

        // When
        List<UserDto> result = userServiceDto.listarUsuarios();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userFeingInterfaz, times(1)).listarUsuarios();
    }
}
