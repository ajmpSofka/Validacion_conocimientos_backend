package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface EditCyclingTeam {
    Mono<CyclingTeamDTO> apply(@Valid CyclingTeamDTO cyclingTeamDTO);
}
