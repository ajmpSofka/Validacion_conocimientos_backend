package co.com.sofka.cyclist_app.usecases;

import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.models.dto.CyclistDTO;
import co.com.sofka.cyclist_app.repositories.CyclingTeamRepository;
import co.com.sofka.cyclist_app.utils.Mapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Service
public class DeleteCyclistUseCase implements Function<List<String>, Mono<CyclingTeamDTO>> {

    private final CyclingTeamRepository cyclingTeamRepository;
    private final CreateCyclingTeamUseCase createCyclingTeamUseCase;
    private final Mapper mapper;
    private final GetCyclistsByIdUseCase getCyclistsByIdUseCase;

    public DeleteCyclistUseCase(CyclingTeamRepository cyclingTeamRepository, CreateCyclingTeamUseCase createCyclingTeamUseCase, Mapper mapper, GetCyclistsByIdUseCase getCyclistsByIdUseCase) {
        this.cyclingTeamRepository = cyclingTeamRepository;
        this.createCyclingTeamUseCase = createCyclingTeamUseCase;
        this.mapper = mapper;
        this.getCyclistsByIdUseCase = getCyclistsByIdUseCase;
    }

    @Override
    public Mono<CyclingTeamDTO> apply(List<String> ids) {
        String idTeam = ids.get(0);
        String idCyclist = ids.get(1);
        String texto="";
        return cyclingTeamRepository.findById(idTeam)
                .flatMap(cyclingTeam -> {
                    List<CyclistDTO> newCyclists = new ArrayList<>();
                    List<CyclistDTO> cyclists = cyclingTeam.getCyclists();
                    cyclists.forEach(cyclistDTO -> {
                        if(!cyclistDTO.getId().equals(idCyclist)){
                            newCyclists.add(cyclistDTO);
                        }
                    });
                    cyclingTeam.setCyclists(newCyclists);
                    CyclingTeamDTO cyclingTeamDTO = mapper.mapEntityToCyclingTeam().apply(cyclingTeam);

                    return createCyclingTeamUseCase.apply(cyclingTeamDTO);
                });
    }

/*
    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        String text="";

        getCyclistsByIdUseCase.apply(id)
                .map(cyclistDTO -> {
                     cyclingTeamRepository.findAll()
                            .filter(cyclingTeam -> !cyclingTeam.getName().equals(cyclistDTO.getPartnerTeam()))
                            .flatMap(cyclingTeam -> {
                                List<CyclistDTO> newCyclist = cyclingTeam.getCyclists();
                                if(newCyclist.remove(cyclistDTO)){
                                    cyclingTeam.setCyclists(newCyclist);
                                }
                                createCyclingTeamUseCase.apply(mapper.mapEntityToCyclingTeam().apply(cyclingTeam));
                                return null;
                            });
                    return null;
                });
        return Mono.just("Hello world!");
    }
    */

}

