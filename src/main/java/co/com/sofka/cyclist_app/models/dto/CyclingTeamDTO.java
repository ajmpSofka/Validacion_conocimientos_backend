package co.com.sofka.cyclist_app.models.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CyclingTeamDTO {


    @Size(max = 3)
    private String id;

    @NotBlank
    private String name;

    private String partnerCountry;

    private List<CyclistDTO> cyclists;

    public CyclingTeamDTO() {}

    public CyclingTeamDTO(String id, String name, String partnerCountry, List<CyclistDTO> cyclists) {
        this.id = id;
        this.name = name;
        this.partnerCountry = partnerCountry;
        this.cyclists = cyclists;
    }
}
