package cl.ecomarket.pedido.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class UserDtoTest {

    @Test
    public void testAllArgsConstructor() {
        // Given
        Integer id = 1;
        String name = "John Doe";
        String email = "john.doe@ejemplo.com";

        // When
        UserDto user = new UserDto(id, name, email);

        // Then
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testNoArgsConstructor() {
        // When
        UserDto user = new UserDto();

        // Then
        assertEquals(null, user.getId());
        assertEquals(null, user.getName());
        assertEquals(null, user.getEmail());
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        UserDto user = new UserDto();
        Integer id = 2;
        String name = "Jane Doe";
        String email = "jane.doe@ejemplo.com";

        // When
        user.setId(id);
        user.setName(name);
        user.setEmail(email);

        // Then
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        UserDto user1 = new UserDto(1, "John Doe", "john.doe@ejemplo.com");
        UserDto user2 = new UserDto(1, "John Doe", "john.doe@ejemplo.com");
        UserDto user3 = new UserDto(2, "Jane Doe", "jane.doe@ejemplo.com");

        // Then
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1, user3);
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        // When
        String result = user.toString();

        // Then
        String expected = "UserDto(id=1, name=John Doe, email=john.doe@ejemplo.com)";
        assertEquals(expected, result);
    }
}
