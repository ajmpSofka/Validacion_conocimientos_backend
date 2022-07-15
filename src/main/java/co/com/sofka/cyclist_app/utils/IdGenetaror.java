package co.com.sofka.cyclist_app.utils;

import co.com.sofka.cyclist_app.models.dto.CyclingTeamDTO;
import co.com.sofka.cyclist_app.models.entities.CyclingTeam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class IdGenetaror {

    public  String generate(Integer digitos) {
        String AlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder(digitos);

        for (int i = 0; i < digitos; i++) {
            int index= (int)(AlphaNumericS.length()* Math.random());
            builder.append(AlphaNumericS.charAt(index));
        }
        return builder.toString();
    }

    public String generateID(List<String> ids,int digitos){
        String id;
        do {
            id = generate(digitos);
        } while (ids.contains(id));
        return id;
    }

}
