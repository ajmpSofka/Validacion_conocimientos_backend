package co.com.sofka.cyclist_app.routers;

import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.usecases.DeleteCyclistUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteCyclistRouter {


    @Bean
    public RouterFunction<ServerResponse> studentGrades(DeleteCyclistUseCase deleteCyclistUseCase) {
        return route(
                DELETE("/cyclist/delete/{idTeam}/{idCyclist}").and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    List<String> idS = new ArrayList<>();
                    idS.add(request.pathVariable("idTeam"));
                    idS.add(request.pathVariable("idCyclist"));
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromPublisher(deleteCyclistUseCase.apply(idS), CyclingTeamDTO.class));
                }
        );
    }
}
