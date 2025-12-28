package Backend.Acetracker.Repository;

import Backend.Acetracker.Entity.InterviewPlanTopicProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewPlanTopicProgressRepository extends JpaRepository<InterviewPlanTopicProgress, Long> {
    List<InterviewPlanTopicProgress> findByInterviewPlanId(Long interviewPlanId);
    Optional<InterviewPlanTopicProgress> findByInterviewPlanIdAndTopicId(Long interviewPlanId, Long topicId);
}

