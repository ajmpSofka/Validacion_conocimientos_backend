package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.utils.IdGenetaror;
import co.com.sofka.cyclist_app.utils.Mapper;
import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.models.entities.CyclingTeam;
import co.com.sofka.cyclist_app.repositories.CyclingTeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateCyclingTeamUseCase implements SaveCyclingTeam {

    private final CyclingTeamRepository cyclingTeamRepository;
    private final Mapper mapper;
    private final IdGenetaror idGenetaror;

    public CreateCyclingTeamUseCase(CyclingTeamRepository cyclingTeamRepository, Mapper mapper, IdGenetaror idGenetaror) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.mapper = mapper;
        this.idGenetaror = idGenetaror;
    }

    @Override
    public Mono<CyclingTeamDTO> apply(CyclingTeamDTO cyclingTeamDTO) {
        if(cyclingTeamDTO.getCyclists() == null){
            cyclingTeamDTO.setCyclists(new ArrayList<>());
        }
        if(cyclingTeamDTO.getId() == null) {
            List<String> ids = cyclingTeamRepository.findAll().map(CyclingTeam::getId).collectList().toFuture().join();
            cyclingTeamDTO.setId(idGenetaror.generateID(ids,3));
        }
        return cyclingTeamRepository.save(mapper.mapperCyclingTeamDTOToEntity().apply(cyclingTeamDTO))
                .map(cyclingTeam1 -> mapper.mapEntityToCyclingTeam().apply(cyclingTeam1));
    }

}
