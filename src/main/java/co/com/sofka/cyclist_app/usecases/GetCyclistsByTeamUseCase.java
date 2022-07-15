package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.models.dto.CyclistDTO;
import co.com.sofka.cyclist_app.models.entities.CyclingTeam;
import co.com.sofka.cyclist_app.repositories.CyclingTeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;
@Service
public class GetCyclistsByTeamUseCase implements Function<String, Flux<CyclistDTO>> {

    private final CyclingTeamRepository cyclingTeamRepository;

    public GetCyclistsByTeamUseCase(CyclingTeamRepository cyclingTeamRepository) {
        this.cyclingTeamRepository = cyclingTeamRepository;
    }

    @Override
    public Flux<CyclistDTO> apply(String id) {

        return cyclingTeamRepository.findById(id)
                .map(CyclingTeam::getCyclists)
                .flatMapMany(Flux::fromIterable);
    }
}
