package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.utils.Mapper;
import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.repositories.CyclingTeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;
@Service
public class GetACyclingTeamUseCase implements Function<String, Mono<CyclingTeamDTO>> {

    private final CyclingTeamRepository cyclingTeamRepository;
    private final Mapper mapper;

    public GetACyclingTeamUseCase(CyclingTeamRepository cyclingTeamRepository, Mapper mapper) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.mapper = mapper;
    }


    @Override
    public Mono<CyclingTeamDTO> apply(String id) {
        return cyclingTeamRepository.findById(id)
                .map(mapper.mapEntityToCyclingTeam());
    }
}
