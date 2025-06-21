package cl.ecomarket.pedido.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class ProductoDtoTest {

    @Test
    public void testAllArgsConstructor() {
        // Given
        Long idProducto = 1L;
        String nombreProducto = "Lechuga";
        int precioProducto = 500;

        // When
        ProductoDto producto = new ProductoDto(idProducto, nombreProducto, precioProducto);

        // Then
        assertEquals(idProducto, producto.getIdProducto());
        assertEquals(nombreProducto, producto.getNombreProducto());
        assertEquals(precioProducto, producto.getPrecioProducto());
    }

    @Test
    public void testNoArgsConstructor() {
        // When
        ProductoDto producto = new ProductoDto();

        // Then
        assertEquals(null, producto.getIdProducto());
        assertEquals(null, producto.getNombreProducto());
        assertEquals(0, producto.getPrecioProducto());
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        ProductoDto producto = new ProductoDto();
        Long idProducto = 2L;
        String nombreProducto = "Piña";
        int precioProducto = 2000;

        // When
        producto.setIdProducto(idProducto);
        producto.setNombreProducto(nombreProducto);
        producto.setPrecioProducto(precioProducto);

        // Then
        assertEquals(idProducto, producto.getIdProducto());
        assertEquals(nombreProducto, producto.getNombreProducto());
        assertEquals(precioProducto, producto.getPrecioProducto());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        ProductoDto producto1 = new ProductoDto(1L, "Producto A", 1000);
        ProductoDto producto2 = new ProductoDto(1L, "Producto A", 1000);
        ProductoDto producto3 = new ProductoDto(2L, "Producto B", 2000);

        // Then
        assertEquals(producto1, producto2);
        assertEquals(producto1.hashCode(), producto2.hashCode());
        assertNotEquals(producto1, producto3);
        assertNotEquals(producto1.hashCode(), producto3.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        ProductoDto producto = new ProductoDto(1L, "Choclo", 1000);

        // When
        String result = producto.toString();

        // Then
        String expected = "ProductoDto(idProducto=1, nombreProducto=Choclo, precioProducto=1000)";
        assertEquals(expected, result);
    }

    @Test
    public void testEqualsSameObject() {
        // Given
        ProductoDto producto = new ProductoDto(1L, "Manzana", 1500);

        // Then
        assertTrue(producto.equals(producto)); // Same object
    }

    @Test
    public void testEqualsDifferentObjectSameValues() {
        // Given
        ProductoDto producto1 = new ProductoDto(1L, "Manzana", 1500);
        ProductoDto producto2 = new ProductoDto(1L, "Manzana", 1500);

        // Then
        assertTrue(producto1.equals(producto2)); // Different object, same values
    }

    @Test
    public void testEqualsDifferentValues() {
        // Given
        ProductoDto producto1 = new ProductoDto(1L, "Manzana", 1500);
        ProductoDto producto2 = new ProductoDto(2L, "Pera", 2000);

        // Then
        assertFalse(producto1.equals(producto2)); // Different values
    }

    @Test
    public void testEqualsNullObject() {
        // Given
        ProductoDto producto = new ProductoDto(1L, "Manzana", 1500);

        // Then
        assertFalse(producto.equals(null)); // Null object
    }

    @Test
    public void testEqualsDifferentClass() {
        // Given
        ProductoDto producto = new ProductoDto(1L, "Manzana", 1500);
        String differentClassObject = "Not a ProductoDto";

        // Then
        assertFalse(producto.equals(differentClassObject)); // Different class
    }

    @Test
    public void testHashCodeSameValues() {
        // Given
        ProductoDto producto1 = new ProductoDto(1L, "Manzana", 1500);
        ProductoDto producto2 = new ProductoDto(1L, "Manzana", 1500);

        // Then
        assertEquals(producto1.hashCode(), producto2.hashCode()); // Same values, same hashCode
    }

    @Test
    public void testHashCodeDifferentValues() {
        // Given
        ProductoDto producto1 = new ProductoDto(1L, "Manzana", 1500);
        ProductoDto producto2 = new ProductoDto(2L, "Pera", 2000);

        // Then
        assertNotEquals(producto1.hashCode(), producto2.hashCode()); // Different values, different hashCode
    }

    @Test
    public void testHashCodeNullValues() {
        // Given
        ProductoDto producto1 = new ProductoDto(null, null, 0);
        ProductoDto producto2 = new ProductoDto(null, null, 0);

        // Then
        assertEquals(producto1.hashCode(), producto2.hashCode()); // Null values, same hashCode
    }

    @Test
    public void testEqualsWithNullAttributes() {
        // Given
        ProductoDto producto1 = new ProductoDto(null, null, 0);
        ProductoDto producto2 = new ProductoDto(null, null, 0);

        // Then
        assertTrue(producto1.equals(producto2)); // Null attributes, same values
    }

    @Test
    public void testToStringWithNullValues() {
        // Given
        ProductoDto producto = new ProductoDto(null, null, 0);

        // When
        String result = producto.toString();

        // Then
        String expected = "ProductoDto(idProducto=null, nombreProducto=null, precioProducto=0)";
        assertEquals(expected, result);
    }
}
