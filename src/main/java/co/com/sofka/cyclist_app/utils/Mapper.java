package co.com.sofka.cyclist_app.utils;

import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.models.entities.CyclingTeam;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Mapper {


    public Function<CyclingTeamDTO, CyclingTeam> mapperCyclingTeamDTOToEntity() {

        return updateCyclingTeam -> {
            var cyclingTeam = new CyclingTeam();
            cyclingTeam.setId(updateCyclingTeam.getId());
            cyclingTeam.setName(updateCyclingTeam.getName());
            cyclingTeam.setPartnerCountry(updateCyclingTeam.getPartnerCountry());
            cyclingTeam.setCyclists(updateCyclingTeam.getCyclists());
            return cyclingTeam;
        };
    }

    public Function<CyclingTeam, CyclingTeamDTO> mapEntityToCyclingTeam() {
        return entity -> new CyclingTeamDTO(
                entity.getId(),
                entity.getName(),
                entity.getPartnerCountry(),
                entity.getCyclists()
        );
    }

}
