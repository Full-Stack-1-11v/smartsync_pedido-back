package cl.ecomarket.pedido.dto;

public class BoletaDto {
    private PedidoDto pedido;
    private ProductoDto producto;
    private UserDto user;
    public PedidoDto getPedido() {
        return pedido;
    }
    public void setPedido(PedidoDto pedido) {
        this.pedido = pedido;
    }
    public ProductoDto getProducto() {
        return producto;
    }
    public void setProducto(ProductoDto producto) {
        this.producto = producto;
    }
    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }

    


}
