package co.com.sofka.cyclist_app.routers;

import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.models.dto.CyclistDTO;
import co.com.sofka.cyclist_app.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetCyclistRouter {

    @Bean
    public RouterFunction<ServerResponse> getCyclist(GetCyclistsUseCase getCyclistsUseCase) {
        return route(GET("/cyclist"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCyclistsUseCase.get(), CyclistDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getCyclistByNationality(GetCyclistsByNationalityUseCase getCyclistsByNationalityUseCase) {
        return route(
                GET("/cyclist/nationality/{nationality}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCyclistsByNationalityUseCase.apply(
                                        request.pathVariable("nationality")),
                                CyclistDTO.class))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> getCyclistById(GetCyclistsByIdUseCase getCyclistsByIdUseCase) {
        return route(
                GET("/cyclist/id/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCyclistsByIdUseCase.apply(
                                        request.pathVariable("id")),
                                CyclistDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getCyclistByTeam(GetCyclistsByTeamUseCase getCyclistsByTeamUseCase) {
        return route(
                GET("/cyclist/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCyclistsByTeamUseCase.apply(
                                        request.pathVariable("id")),
                                CyclistDTO.class))
        );
    }
}
