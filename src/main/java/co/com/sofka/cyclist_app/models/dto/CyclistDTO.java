package co.com.sofka.cyclist_app.models.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CyclistDTO {

    @Id
    @Size(max = 3)
    private String id;

    @NotBlank
    private String name;

    private String partnerTeam;

    @NotBlank
    private String nationality;

    public CyclistDTO() {
    }

    public CyclistDTO(@Size(max = 3) String id, String name, String partnerTeam, String nationality) {
        this.id = id;
        this.name = name;
        this.partnerTeam = partnerTeam;
        this.nationality = nationality;
    }
}
