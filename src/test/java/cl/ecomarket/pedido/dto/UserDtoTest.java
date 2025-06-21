package cl.ecomarket.pedido.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

=======
>>>>>>> a93048194b3d1cfcfae95904fc82b9a87d6b393b
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
<<<<<<< HEAD

    @Test
    public void testEqualsSameObject() {
        // Given
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        // Then
        assertTrue(user.equals(user)); // Same object
    }

    @Test
    public void testEqualsDifferentObjectSameValues() {
        // Given
        UserDto user1 = new UserDto(1, "John Doe", "john.doe@ejemplo.com");
        UserDto user2 = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        // Then
        assertTrue(user1.equals(user2)); // Different object, same values
    }

    @Test
    public void testEqualsDifferentValues() {
        // Given
        UserDto user1 = new UserDto(1, "John Doe", "john.doe@ejemplo.com");
        UserDto user2 = new UserDto(2, "Jane Doe", "jane.doe@ejemplo.com");

        // Then
        assertFalse(user1.equals(user2)); // Different values
    }

    @Test
    public void testEqualsNullObject() {
        // Given
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        // Then
        assertFalse(user.equals(null)); // Null object
    }

    @Test
    public void testEqualsDifferentClass() {
        // Given
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");
        String differentClassObject = "Not a UserDto";

        // Then
        assertFalse(user.equals(differentClassObject)); // Different class
    }

    @Test
    public void testHashCodeSameValues() {
        // Given
        UserDto user1 = new UserDto(1, "John Doe", "john.doe@ejemplo.com");
        UserDto user2 = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        // Then
        assertEquals(user1.hashCode(), user2.hashCode()); // Same values, same hashCode
    }

    @Test
    public void testHashCodeDifferentValues() {
        // Given
        UserDto user1 = new UserDto(1, "John Doe", "john.doe@ejemplo.com");
        UserDto user2 = new UserDto(2, "Jane Doe", "jane.doe@ejemplo.com");

        // Then
        assertNotEquals(user1.hashCode(), user2.hashCode()); // Different values, different hashCode
    }

    @Test
    public void testHashCodeNullValues() {
        // Given
        UserDto user1 = new UserDto(null, null, null);
        UserDto user2 = new UserDto(null, null, null);

        // Then
        assertEquals(user1.hashCode(), user2.hashCode()); // Null values, same hashCode
    }

    @Test
    public void testEqualsWithNullAttributes() {
        // Given
        UserDto user1 = new UserDto(null, null, null);
        UserDto user2 = new UserDto(null, null, null);

        // Then
        assertTrue(user1.equals(user2)); // Null attributes, same values
    }

    @Test
    public void testToStringWithNullValues() {
        // Given
        UserDto user = new UserDto(null, null, null);

        // When
        String result = user.toString();

        // Then
        String expected = "UserDto(id=null, name=null, email=null)";
        assertEquals(expected, result);
    }

    @Test
    public void testEqualsPartialNullAttributes() {
        // Given
        UserDto user1 = new UserDto(1, null, "john.doe@ejemplo.com");
        UserDto user2 = new UserDto(1, null, "john.doe@ejemplo.com");

        // Then
        assertTrue(user1.equals(user2)); // Partial null attributes, same values
    }

    @Test
    public void testHashCodePartialNullAttributes() {
        // Given
        UserDto user1 = new UserDto(1, null, "john.doe@ejemplo.com");
        UserDto user2 = new UserDto(1, null, "john.doe@ejemplo.com");

        // Then
        assertEquals(user1.hashCode(), user2.hashCode()); // Partial null attributes, same hashCode
    }
=======
>>>>>>> a93048194b3d1cfcfae95904fc82b9a87d6b393b
}
