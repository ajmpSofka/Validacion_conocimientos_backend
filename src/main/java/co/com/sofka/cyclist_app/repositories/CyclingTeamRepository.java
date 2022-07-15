package co.com.sofka.cyclist_app.repositories;

import co.com.sofka.cyclist_app.models.entities.CyclingTeam;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CyclingTeamRepository extends ReactiveMongoRepository<CyclingTeam, String> {
}
