package ma.youcode.managment_tournoi_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import ma.youcode.managment_tournoi_backend.entity.enums.LevelEnum;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamGroup {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isPassed;
    private Integer points;
    private Integer wins;
    private Integer draws;
    private Integer losses;
    private Integer rank;
    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;
    @ManyToOne
    private Group group;
}
