package ma.youcode.managment_tournoi_backend.dto.participantDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ma.youcode.managment_tournoi_backend.dto.teamDto.TeamUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
@Data
@Builder
public class ParticipantUpdateDto {
    @NotNull(message = "IDs of participants is required")
    @NotBlank(message = "IDs of participants should not be blank")
    private List<UUID> usersIds;
    @NotNull(message = "Team is required")
    @NotBlank(message = "Team should not be blank")
    private TeamUpdateDto team;
    @NotNull(message = "logo is required")
    @NotBlank(message = "logo should not be blank")
    private MultipartFile logo;
}
