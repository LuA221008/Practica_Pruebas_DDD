package co.com.sofka.comercial.venta.events;

import co.com.sofka.comercial.venta.values.Color;
import co.com.sofka.comercial.venta.values.ProductoId;
import co.com.sofka.comercial.venta.values.Talla;
import co.com.sofka.domain.generic.DomainEvent;

public class ProductoActualizado extends DomainEvent {
    private final ProductoId entityId;
    private final Talla talla;
    private final Color color;

    public ProductoActualizado(ProductoId entityId, Talla talla) {
        super("co.com.sofka.comercial.ProductoActualizado");
        this.entityId = entityId;
        this.talla = talla;
        color = null;
    }

    public ProductoActualizado(ProductoId entityId, Color color) {
        super("co.com.sofka.comercial.ProductoActualizado");
        this.entityId = entityId;
        this.color = color;
        talla = null;
    }

    public ProductoId getEntityId() {
        return entityId;
    }

    public Talla getTalla() {
        return talla;
    }

    public Color getColor() {
        return color;
    }
}
