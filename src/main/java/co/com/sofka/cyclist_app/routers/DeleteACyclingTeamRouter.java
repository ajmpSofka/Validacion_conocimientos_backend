package co.com.sofka.cyclist_app.routers;

import co.com.sofka.cyclist_app.usecases.DeleteACyclingTeamUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteACyclingTeamRouter {

    @Bean
    public RouterFunction<ServerResponse> delete(DeleteACyclingTeamUseCase deleteACyclingTeamUseCase) {
        return route(
                DELETE("/team/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteACyclingTeamUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }

}






