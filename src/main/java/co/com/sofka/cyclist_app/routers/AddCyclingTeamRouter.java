package co.com.sofka.cyclist_app.routers;

import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.usecases.CreateCyclingTeamUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AddCyclingTeamRouter {

    @Bean
    public RouterFunction<ServerResponse> AddCyclingTeam(CreateCyclingTeamUseCase addCyclingTeamUseCase) {

        Function<CyclingTeamDTO, Mono<ServerResponse>> executor = cyclingTeamDTO ->  addCyclingTeamUseCase
                .apply(cyclingTeamDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));
        return route(
                POST("/team/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclingTeamDTO.class).flatMap(executor)
        );
    }


}

