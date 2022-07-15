package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.models.entities.CyclingTeam;
import co.com.sofka.cyclist_app.utils.IdGenetaror;
import co.com.sofka.cyclist_app.utils.Mapper;
import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.models.dto.CyclistDTO;
import co.com.sofka.cyclist_app.repositories.CyclingTeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
@Service
public class AddCyclistUseCase implements AddCyclist{

    private final CyclingTeamRepository cyclingTeamRepository;
    private final Mapper mapper;
    private final CreateCyclingTeamUseCase createCyclingTeamUseCase;
    private final IdGenetaror idGenetaror;
    private final GetCyclistsUseCase getCyclistsUseCase;

    public AddCyclistUseCase(CyclingTeamRepository cyclingTeamRepository, Mapper mapper, CreateCyclingTeamUseCase createCyclingTeamUseCase, IdGenetaror idGenetaror, GetCyclistsUseCase getCyclistsUseCase) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.mapper = mapper;
        this.createCyclingTeamUseCase = createCyclingTeamUseCase;
        this.idGenetaror = idGenetaror;
        this.getCyclistsUseCase = getCyclistsUseCase;
    }

    @Override
    public Mono<CyclingTeamDTO> apply(CyclistDTO cyclistDTO, String idTeam) {
        return cyclingTeamRepository.findById(idTeam)
                .flatMap(cyclingTeam -> {
                    List<CyclistDTO> cyclists = cyclingTeam.getCyclists();
                    if(cyclists.size() < 8){
                        cyclists.add(new CyclistDTO(idGenetaror.generateID(new ArrayList<>(),3),cyclistDTO.getName(),cyclingTeam.getName(),cyclistDTO.getNationality()));
                        cyclingTeam.setCyclists(cyclists);
                    }
                    return createCyclingTeamUseCase.apply(mapper.mapEntityToCyclingTeam().apply(cyclingTeam));
                });
    }
}
