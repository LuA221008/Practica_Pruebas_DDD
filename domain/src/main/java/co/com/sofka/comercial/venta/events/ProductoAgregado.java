package co.com.sofka.comercial.venta.events;

import co.com.sofka.comercial.venta.values.Color;
import co.com.sofka.comercial.venta.values.Precio;
import co.com.sofka.comercial.venta.values.ProductoId;
import co.com.sofka.comercial.venta.values.Talla;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.generic.values.Nombre;


public class ProductoAgregado extends DomainEvent {
    private final ProductoId productoId;
    private final Nombre nombre;
    private final Talla talla;
    private final Color color;
    private final Precio precio;

    public ProductoAgregado(ProductoId productoId, Nombre nombre, Talla talla, Color color, Precio precio) {
        super("co.com.sofka.comercial.ProductoAgregado");
        this.productoId = productoId;
        this.nombre = nombre;
        this.talla = talla;
        this.color = color;
        this.precio = precio;
    }



    public ProductoId getProductoId() {
        return productoId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Talla getTalla() {
        return talla;
    }

    public Color getColor() {
        return color;
    }

    public Precio getPrecio() {
        return precio;
    }
}
