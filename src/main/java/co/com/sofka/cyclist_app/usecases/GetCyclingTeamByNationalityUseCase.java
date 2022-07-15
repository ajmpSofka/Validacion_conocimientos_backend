package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;
@Service
public class GetCyclingTeamByNationalityUseCase implements Function<String, Flux<CyclingTeamDTO>> {

    private final GetCyclingTeamsUseCase getCyclingTeamsUseCase;

    public GetCyclingTeamByNationalityUseCase(GetCyclingTeamsUseCase getCyclingTeamsUseCase) {
        this.getCyclingTeamsUseCase = getCyclingTeamsUseCase;
    }

    @Override
    public Flux<CyclingTeamDTO> apply(String country) {
        return getCyclingTeamsUseCase.get()
                .filter(cyclingTeamDTO -> cyclingTeamDTO.getPartnerCountry().toLowerCase().equals(country.toLowerCase()));
    }
}
