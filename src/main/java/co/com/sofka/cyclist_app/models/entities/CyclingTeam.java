package co.com.sofka.cyclist_app.models.entities;

import co.com.sofka.cyclist_app.models.dto.CyclistDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.Size;
import java.util.List;

@Data
@Document
public class CyclingTeam {

    @Id
    @Size(max = 3)
    private String id;


    private String name;

    private String partnerCountry;

    private List<CyclistDTO> cyclists;

    public CyclingTeam() {}

    public CyclingTeam(String id, String name, String partnerCountry, List<CyclistDTO> cyclists) {
        this.id = id;
        this.name = name;
        this.partnerCountry = partnerCountry;
        this.cyclists = cyclists;
    }
}
