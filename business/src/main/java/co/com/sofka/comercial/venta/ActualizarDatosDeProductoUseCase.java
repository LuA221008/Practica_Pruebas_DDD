package co.com.sofka.comercial.venta;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.comercial.venta.commands.ActualizarDatosProducto;

public class ActualizarDatosDeProductoUseCase extends UseCase<RequestCommand<ActualizarDatosProducto>, ResponseEvents> {


    @Override
    public void executeUseCase(RequestCommand<ActualizarDatosProducto> actualizarDatosProductoRequestCommand) {
        var command = actualizarDatosProductoRequestCommand.getCommand();
        var venta = Venta.from(
                command.getVentaId(), repository().getEventsBy(command.getVentaId().value())
        );
        venta.actualizarDatosProducto(command.getProductoId(),command.getColor());

        emit().onResponse(new ResponseEvents(venta.getUncommittedChanges()));
    }

    }

