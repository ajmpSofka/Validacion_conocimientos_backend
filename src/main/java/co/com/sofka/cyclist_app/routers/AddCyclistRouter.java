package co.com.sofka.cyclist_app.routers;

import co.com.sofka.cyclist_app.models.dto.CyclistDTO;
import co.com.sofka.cyclist_app.usecases.AddCyclistUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AddCyclistRouter {

    @Bean
    public RouterFunction<ServerResponse> addCyclist(AddCyclistUseCase addCyclistUseCase) {
        return route(
                PUT("/cyclist/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    String idCyclingTeam = request.pathVariable("id");
                    return request.bodyToMono(CyclistDTO.class)
                            .flatMap(cyclistDTO ->
                                    addCyclistUseCase.apply(cyclistDTO, idCyclingTeam)
                                            .flatMap(result -> ServerResponse.ok()
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .bodyValue(result)));
                }
        );
    }
}
