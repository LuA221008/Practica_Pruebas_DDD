package co.com.sofka.comercial.venta;

import co.com.sofka.comercial.venta.events.*;
import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.generic.values.Fecha;
import co.com.sofka.generic.values.Nombre;

import java.util.ArrayList;

public class VentaEventChange extends EventChange {
    public VentaEventChange(Venta venta) {
        apply((VentaCreada event) -> {
            venta.fecha = event.getFecha();
            venta.total = event.getTotal();
            venta.productos = new ArrayList<>();
        });

        apply((ClienteAsignado event) -> {
            venta.cliente.nombre = (Nombre) event.getNombre().value();
            venta.cliente.telefono = event.getTelefono();
        });

        apply((ProductoAgregado event) -> {
            var producto = new Producto(event.getProductoId(), event.getNombre(), event.getTalla(), event.getColor(), event.getPrecio());
            venta.productos.add(producto);
        });

        apply((CuponAsignado event) -> {
            venta.cupon.descuento = event.getDescuento();
            venta.cupon.fecha = event.getFecha();
        });

        apply((VentaRegistrada event) -> {
            venta.total = event.getTotal();
        });

    }
}
