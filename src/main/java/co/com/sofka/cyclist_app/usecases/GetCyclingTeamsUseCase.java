package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.utils.Mapper;
import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.repositories.CyclingTeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;
@Service
public class GetCyclingTeamsUseCase implements Supplier<Flux<CyclingTeamDTO>> {

    private final CyclingTeamRepository cyclingTeamRepository;
    private final Mapper mapper;

    public GetCyclingTeamsUseCase(CyclingTeamRepository cyclingTeamRepository, Mapper mapper) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.mapper = mapper;
    }

    @Override
    public Flux<CyclingTeamDTO> get() {
        return cyclingTeamRepository.findAll()
                .map(mapper.mapEntityToCyclingTeam());
    }
}
