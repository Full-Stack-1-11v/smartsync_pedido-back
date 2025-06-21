package cl.ecomarket.pedido.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoletaDtoTest {

    @Test
    public void testSettersAndGetters() {
        // Given
        PedidoDto pedido = new PedidoDto(1L, true, java.sql.Date.valueOf("2025-06-05"));
        ProductoDto producto = new ProductoDto(1L, "Tomate", 400);
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        BoletaDto boleta = new BoletaDto();

        // When
        boleta.setPedido(pedido);
        boleta.setProducto(producto);
        boleta.setUser(user);

        // Then
        assertEquals(pedido, boleta.getPedido());
        assertEquals(producto, boleta.getProducto());
        assertEquals(user, boleta.getUser());
    }
}
