package cl.ecomarket.pedido.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
}
