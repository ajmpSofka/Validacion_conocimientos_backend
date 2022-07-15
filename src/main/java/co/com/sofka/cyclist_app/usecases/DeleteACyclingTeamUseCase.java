package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.utils.Mapper;
import co.com.sofka.cyclist_app.repositories.CyclingTeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
public class DeleteACyclingTeamUseCase implements Function<String, Mono<Void>> {

    private final CyclingTeamRepository cyclingTeamRepository;

    public DeleteACyclingTeamUseCase(CyclingTeamRepository cyclingTeamRepository, Mapper mapper) {
        this.cyclingTeamRepository = cyclingTeamRepository;
    }


    @Override
    public Mono<Void>  apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return cyclingTeamRepository.deleteById(id);
    }
}


