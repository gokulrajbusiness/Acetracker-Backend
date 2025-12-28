package Backend.Acetracker.Repository;

import Backend.Acetracker.Entity.InterviewPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewPlanRepository extends JpaRepository<InterviewPlan, Long> {
    List<InterviewPlan> findByUserId(Long userId);
}
