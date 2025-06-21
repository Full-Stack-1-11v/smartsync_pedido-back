package cl.ecomarket.pedido.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import cl.ecomarket.pedido.model.Pedido;

@SpringBootTest
public class PedidoRepositoryTest {

    @MockBean
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoRepositoryTest pedidoRepositoryTest;

    @Test
    public void testFindByPedidoId() {
        // Given
        Long pedidoId = 1L;
        Pedido pedido = new Pedido(pedidoId, true, Date.valueOf("2025-06-05"));
        when(pedidoRepository.findByPedidoId(pedidoId)).thenReturn(pedido);

        // When
        Pedido result = pedidoRepository.findByPedidoId(pedidoId);

        // Then
        assertEquals(pedido, result);
        verify(pedidoRepository).findByPedidoId(pedidoId); // Verifica que el método fue llamado
    }

    @Test
    public void testFindByPedidoIdNotFound() {
        // Given
        Long pedidoId = 999L;
        when(pedidoRepository.findByPedidoId(pedidoId)).thenReturn(null);

        // When
        Pedido result = pedidoRepository.findByPedidoId(pedidoId);

        // Then
        assertNull(result);
        verify(pedidoRepository).findByPedidoId(pedidoId); // Verifica que el método fue llamado
    }

    @Test
    public void testSavePedido() {
        // Given
        Pedido pedido = new Pedido(null, false, Date.valueOf("2025-06-05"));
        Pedido savedPedido = new Pedido(1L, false, Date.valueOf("2025-06-05"));
        when(pedidoRepository.save(pedido)).thenReturn(savedPedido);

        // When
        Pedido result = pedidoRepository.save(pedido);

        // Then
        assertEquals(savedPedido, result);
        verify(pedidoRepository).save(pedido); // Verifica que el método fue llamado
    }

    @Test
    public void testFindAllPedidos() {
        // Given
        Pedido pedido1 = new Pedido(1L, true, Date.valueOf("2025-06-05"));
        Pedido pedido2 = new Pedido(2L, false, Date.valueOf("2025-06-06"));
        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);
        when(pedidoRepository.findAll()).thenReturn(pedidos);

        // When
        List<Pedido> result = pedidoRepository.findAll();

        // Then
        assertEquals(pedidos, result);
        verify(pedidoRepository).findAll(); // Verifica que el método fue llamado
    }

    @Test
    public void testDeleteById() {
        // Given
        Long pedidoId = 1L;

        // When
        pedidoRepository.deleteById(pedidoId);

        // Then
        verify(pedidoRepository).deleteById(pedidoId); // Verifica que el método fue llamado
    }
}
