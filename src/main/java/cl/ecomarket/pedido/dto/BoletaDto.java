package cl.ecomarket.pedido.dto;

/**
 * DTO que representa una boleta en el sistema.
 * <p>
 * Contiene la información relacionada al {@link PedidoDto}, {@link ProductoDto} y {@link UserDto} asociados a la boleta.
 * </p>
 */
public class BoletaDto {

    /**
     * {@link PedidoDto} asociado a la boleta.
     */
    private PedidoDto pedido;

    /**
     * {@link ProductoDto} asociado a la boleta.
     */
    private ProductoDto producto;

    /**
     * {@link UserDto} asociado a la boleta.
     */
    private UserDto user;

    /**
     * Constructor por defecto requerido por JavaBean.
     */
    public BoletaDto() {
        // Constructor por defecto
    }

    /**
     * Obtiene el {@link PedidoDto} asociado a la boleta.
     * 
     * @return el pedido de la boleta.
     */
    public PedidoDto getPedido() {
        return pedido;
    }

    /**
     * Establece el {@link PedidoDto} asociado a la boleta.
     * 
     * @param pedido el pedido a asociar.
     */
    public void setPedido(PedidoDto pedido) {
        this.pedido = pedido;
    }

    /**
     * Obtiene el {@link ProductoDto} asociado a la boleta.
     * 
     * @return el producto de la boleta.
     */
    public ProductoDto getProducto() {
        return producto;
    }

    /**
     * Establece el {@link ProductoDto} asociado a la boleta.
     * 
     * @param producto el producto a asociar.
     */
    public void setProducto(ProductoDto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene el {@link UserDto} asociado a la boleta.
     * 
     * @return el usuario de la boleta.
     */
    public UserDto getUser() {
        return user;
    }

    /**
     * Establece el {@link UserDto} asociado a la boleta.
     * 
     * @param user el usuario a asociar.
     */
    public void setUser(UserDto user) {
        this.user = user;
    }

}
