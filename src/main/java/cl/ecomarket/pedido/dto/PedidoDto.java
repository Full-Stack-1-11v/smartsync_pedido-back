package cl.ecomarket.pedido.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {
    private Long pedidoId;
    private boolean estadoPedido;
    private Date fechaPedido;

}
