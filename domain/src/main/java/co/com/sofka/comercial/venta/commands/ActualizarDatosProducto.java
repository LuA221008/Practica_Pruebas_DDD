package co.com.sofka.comercial.venta.commands;

import co.com.sofka.comercial.venta.values.Color;
import co.com.sofka.comercial.venta.values.ProductoId;
import co.com.sofka.comercial.venta.values.Talla;
import co.com.sofka.comercial.venta.values.VentaId;
import co.com.sofka.domain.generic.Command;

public class ActualizarDatosProducto extends Command {
    private final VentaId ventaId;
    private final Talla talla;
    private final ProductoId productoId;
    private final Color color;

    public ActualizarDatosProducto(VentaId ventaId,ProductoId productoId, Talla talla) {
        this.ventaId = ventaId;
        this.talla = talla;
        this.productoId=productoId;
        color=null;

    }

    public ActualizarDatosProducto(VentaId ventaId, ProductoId productoId, Color color) {
        this.ventaId = ventaId;
        talla = null;
        this.productoId=productoId;
        this.color = color;
    }
    public ActualizarDatosProducto(VentaId ventaId, Talla talla, ProductoId productoId, Color color) {
        this.ventaId = ventaId;
        this.talla = talla;
        this.productoId = productoId;
        this.color = color;
    }

    public VentaId getVentaId() {
        return ventaId;
    }

    public Talla getTalla() {
        return talla;
    }

    public ProductoId getProductoId() {
        return productoId;
    }

    public Color getColor() {
        return color;
    }
}
