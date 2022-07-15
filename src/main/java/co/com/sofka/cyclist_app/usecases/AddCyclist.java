package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.models.dto.CyclistDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface AddCyclist {
    Mono<CyclingTeamDTO> apply(@Valid CyclistDTO cyclistDTO, @Valid String idTeam);
}
