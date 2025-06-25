package cl.ecomarket.pedido.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.ecomarket.pedido.model.Pedido;
import cl.ecomarket.pedido.repository.PedidoRepository;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Test
    public void testGetPedidos() {
        // Given
        List<Pedido> pedidos = new ArrayList<>();
        
        // When
        when(pedidoRepository.findAll()).thenReturn(pedidos);

        // Then
        List<Pedido> result = pedidoService.findAll();

        assertEquals(pedidos, result);
        assertEquals(0, result.size());
    }

    @Test
    public void testFindByPedidoId() {
        // Given
        Long pedidoId = 1L;
        Pedido pedido = new Pedido();
        pedido.setPedidoId(pedidoId);

        // When
        when(pedidoRepository.findByPedidoId(pedidoId)).thenReturn(pedido);

        // Then
        Pedido result = pedidoService.findByPedidoId(pedidoId);

        assertEquals(pedido, result);
    }

    @Test
    public void testGestionarEnvio() {
        // Given
        Long pedidoId = 1L;
        Pedido pedido = new Pedido();
        pedido.setPedidoId(pedidoId);
        pedido.setEstadoPedido(false);

        // When
        when(pedidoRepository.findByPedidoId(pedidoId)).thenReturn(pedido);
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        // Then
        Pedido result = pedidoService.gestionarEnvio(pedidoId);

        assertEquals(true, result.isEstadoPedido());
    }

    @Test
        public void testGestionarEnvioPedidoNoExiste() {
        // Given
        Long pedidoId = 999L; // Un ID que no existe
        when(pedidoRepository.findByPedidoId(pedidoId)).thenReturn(null);

        // When
        Pedido result = pedidoService.gestionarEnvio(pedidoId);

        // Then
        assertEquals(null, result); // Verifica que el resultado sea null
    }

    @Test
    public void testGuardarPedido() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);

        // When
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        // Then
        Pedido result = pedidoService.guardarPedido(pedido);

        assertEquals(pedido, result);
    }

    @Test
    public void testEliminarPedido() {
        // Given
        Long pedidoId = 1L;

        // When
        pedidoService.eliminarPedido(pedidoId);

        // Then
        // Verify that the repository's deleteById method was called
        verify(pedidoRepository).deleteById(pedidoId);
    }
}
