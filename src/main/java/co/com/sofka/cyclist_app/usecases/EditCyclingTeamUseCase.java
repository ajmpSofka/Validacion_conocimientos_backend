package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.utils.Mapper;
import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.repositories.CyclingTeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
@Service
public class EditCyclingTeamUseCase implements EditCyclingTeam{

    private final CyclingTeamRepository cyclingTeamRepository;
    private final Mapper mapper;

    private final CreateCyclingTeamUseCase createCyclingTeamUseCase;

    public EditCyclingTeamUseCase(CyclingTeamRepository cyclingTeamRepository, Mapper mapper, CreateCyclingTeamUseCase createCyclingTeamUseCase) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.mapper = mapper;
        this.createCyclingTeamUseCase = createCyclingTeamUseCase;
    }

    @Override
    public Mono<CyclingTeamDTO> apply(CyclingTeamDTO cyclingTeamDTO) {
        Objects.requireNonNull(cyclingTeamDTO, "Es necesario recibir un objeto de tipo cyclimTeam");

        return cyclingTeamRepository.findById(cyclingTeamDTO.getId())
                .map(cyclingTeam -> mapper.mapEntityToCyclingTeam().apply(cyclingTeam))
                .map(cyclingTeamDTOoriginal -> {
                    cyclingTeamDTOoriginal.setName(cyclingTeamDTO.getName());
                    cyclingTeamDTOoriginal.setPartnerCountry(cyclingTeamDTO.getPartnerCountry());
                    return cyclingTeamDTOoriginal;
                })
                .flatMap(cyclingTeamDTO1 -> createCyclingTeamUseCase.apply(cyclingTeamDTO));
    }
}
