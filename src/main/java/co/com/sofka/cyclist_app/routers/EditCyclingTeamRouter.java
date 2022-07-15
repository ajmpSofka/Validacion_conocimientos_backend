package co.com.sofka.cyclist_app.routers;

import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.usecases.EditCyclingTeamUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class EditCyclingTeamRouter {

    @Bean
    public RouterFunction<ServerResponse> editCyclingTeam(EditCyclingTeamUseCase editCyclingTeamUseCase) {

        Function<CyclingTeamDTO, Mono<ServerResponse>> executor = cyclingTeamDTO ->  editCyclingTeamUseCase
                .apply(cyclingTeamDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));
        return route(
                PUT("/team").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclingTeamDTO.class).flatMap(executor)
        );
    }

}
