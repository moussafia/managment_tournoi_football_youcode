package ma.youcode.managment_tournoi_backend.repository;

import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.Participant;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.entity.embedded.ParticipantEmbeddedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ParticipentRepository extends JpaRepository<Participant, ParticipantEmbeddedId> {
    Optional<Participant> findByTeamAndUser(Team team, AppUser user);
    Optional<List<Participant>> findByTeamId(UUID teamId);
    @Query("SELECT p FROM Participant p WHERE p.participantEmbeddedId.userId = :userId ORDER BY p.dateOfCreation DESC")
    Optional<Participant> findByUserIdOrderByDateOfCreationDesc(@Param("userId") UUID userId);
}
