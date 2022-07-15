package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.models.dto.CyclistDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
public class GetCyclistsByNationalityUseCase implements Function<String, Flux<CyclistDTO>> {

    private final GetCyclistsUseCase getCyclistsUseCase;

    public GetCyclistsByNationalityUseCase(GetCyclistsUseCase getCyclistsUseCase) {
        this.getCyclistsUseCase = getCyclistsUseCase;
    }

    @Override
    public Flux<CyclistDTO> apply(String nationality) {
        return getCyclistsUseCase.get()
                .filter(cyclistDTO -> cyclistDTO.getNationality().toLowerCase().equals(nationality.toLowerCase()));
    }
}
