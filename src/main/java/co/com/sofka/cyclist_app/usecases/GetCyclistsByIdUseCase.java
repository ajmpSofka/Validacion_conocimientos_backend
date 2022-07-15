package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.models.dto.CyclistDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class GetCyclistsByIdUseCase implements Function<String, Flux<CyclistDTO>> {
    private final GetCyclistsUseCase getCyclistsUseCase;


    public GetCyclistsByIdUseCase(GetCyclistsUseCase getCyclistsUseCase) {
        this.getCyclistsUseCase = getCyclistsUseCase;
    }

    @Override
    public Flux<CyclistDTO> apply(String id) {
        return getCyclistsUseCase.get()
                .filter(cyclistDTO -> cyclistDTO.getId().equals(id));
    }
}
