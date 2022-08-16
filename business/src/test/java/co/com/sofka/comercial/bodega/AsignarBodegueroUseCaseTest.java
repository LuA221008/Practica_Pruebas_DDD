package co.com.sofka.comercial.bodega;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.comercial.bodega.commands.AsignarBodeguero;
import co.com.sofka.comercial.bodega.events.BodegaCreada;
import co.com.sofka.comercial.bodega.events.BodegueroAsignado;
import co.com.sofka.comercial.bodega.values.BodegaId;
import co.com.sofka.comercial.bodega.values.Dimension;
import co.com.sofka.comercial.bodega.values.Salario;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.generic.values.Nombre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AsignarBodegueroUseCaseTest {
    @InjectMocks
    private AsignarBodegueroUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void asignarBodegueroHappyPass() {
        //arrange
        var bodegaId= BodegaId.of("bodega1");
        var nombreBodeguero = new Nombre("Carlos","Perez");
        var saliroBodeguero = new Salario("pesos",500.0);
        var command = new AsignarBodeguero(bodegaId,nombreBodeguero,saliroBodeguero);

        when(repository.getEventsBy("bodega1")).thenReturn(history());
        useCase.addRepository(repository);

        //act
        var events = UseCaseHandler.getInstance()// crear una instancia de usecase
                .setIdentifyExecutor(command.getBodegaId().value())// identificamos el command
                .syncExecutor(useCase, new RequestCommand<>(command))// ejecutamos command ese caso de uso
                .orElseThrow()//verificar que si vayan los datos y validaciones
                .getDomainEvents();//ejecutamos ese evento de dominio

        //assert
        var event = ( BodegueroAsignado ) events.get(0);
        Assertions.assertEquals("Carlos", event.getNombre().value().nombre());
        Assertions.assertEquals(500.0+"pesos", event.getSalario().value());

    }

    private List<DomainEvent> history() {
        var dimension = new Dimension(2.8F, 2.5F, 2.8F);
        var event = new BodegaCreada(
                dimension);
        event.setAggregateRootId("bodega1");
        return List.of(event);
    }

}