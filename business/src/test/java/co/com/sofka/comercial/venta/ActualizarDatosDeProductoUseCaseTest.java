package co.com.sofka.comercial.venta;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.comercial.tienda.values.TiendaId;
import co.com.sofka.comercial.venta.commands.ActualizarDatosProducto;
import co.com.sofka.comercial.venta.events.ProductoActualizado;
import co.com.sofka.comercial.venta.events.ProductoAgregado;
import co.com.sofka.comercial.venta.events.VentaCreada;
import co.com.sofka.comercial.venta.values.*;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.generic.values.Fecha;
import co.com.sofka.generic.values.Nombre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActualizarDatosDeProductoUseCaseTest {
    @InjectMocks
    ActualizarDatosDeProductoUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    public void ActualizarDatosDeProducto() {
        //arrange
        var ventaId = VentaId.of("venta1");
        var productoId = ProductoId.of("prodcuto1");
        var color = new Color("Azul");
        var command = new ActualizarDatosProducto(ventaId,productoId,color);

        when(repository.getEventsBy("venta1")).thenReturn(history());
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getVentaId().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = ( ProductoActualizado) events.get(0);
        Assertions.assertEquals("Azul", event.getColor().value());




    }


    private List<DomainEvent> history(){
        var tiendaId = TiendaId.of("zzzz");
        var fecha = new Fecha(LocalDateTime.of(2022, 05, 20, 9, 50));
        var total = new Total(200000D);
        var event = new VentaCreada(
                tiendaId,
                fecha,
                total);

        var event2 = new ProductoAgregado(ProductoId.of("producto1")
                , new Nombre("Camisa", "StudioF")
                , new Talla(Talla.Tallas.L), new Color("negro")
                , new Precio("pesos", 2000.0));

        event.setAggregateRootId("venta1");

        return List.of(event, event2);
    }
}