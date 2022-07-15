package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.models.dto.CyclistDTO;
import co.com.sofka.cyclist_app.models.entities.CyclingTeam;
import co.com.sofka.cyclist_app.repositories.CyclingTeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Stream;
@Service
public class GetCyclistsUseCase implements Supplier<Flux<CyclistDTO>> {

    private final CyclingTeamRepository cyclingTeamRepository;

    public GetCyclistsUseCase(CyclingTeamRepository cyclingTeamRepository) {
        this.cyclingTeamRepository = cyclingTeamRepository;
    }

    @Override
    public Flux<CyclistDTO> get() {
        return cyclingTeamRepository.findAll()
                .flatMap( cyclingTeam -> Flux.fromIterable(cyclingTeam.getCyclists()));
    }

}
