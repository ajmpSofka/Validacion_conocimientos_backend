package co.com.sofka.cyclist_app.routers;

import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.models.dto.CyclistDTO;
import co.com.sofka.cyclist_app.usecases.GetACyclingTeamUseCase;
import co.com.sofka.cyclist_app.usecases.GetCyclingTeamByNationalityUseCase;
import co.com.sofka.cyclist_app.usecases.GetCyclingTeamsUseCase;
import co.com.sofka.cyclist_app.usecases.GetCyclistsByNationalityUseCase;
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
public class GetCyclingTeamsRouter {

    @Bean
    public RouterFunction<ServerResponse> getCyclingTeams(GetCyclingTeamsUseCase getCyclingTeamsUseCase) {
        return route(GET("/team"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCyclingTeamsUseCase.get(), CyclingTeamDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getCyclingTeam(GetACyclingTeamUseCase getACyclingTeamUseCase) {
        return route(
                GET("/team/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getACyclingTeamUseCase.apply(
                                        request.pathVariable("id")),
                                CyclingTeamDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getCyclistTeamByCountry(GetCyclingTeamByNationalityUseCase getCyclingTeamByNationalityUseCase) {
        return route(
                GET("/team/country/{country}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCyclingTeamByNationalityUseCase.apply(
                                        request.pathVariable("country")),
                                CyclingTeamDTO.class))
        );
    }
}
