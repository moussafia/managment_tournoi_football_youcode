package ma.youcode.managment_tournoi_backend.repository;

import ma.youcode.managment_tournoi_backend.entity.Rules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RulesRepository extends JpaRepository<Rules, Long> {
}
