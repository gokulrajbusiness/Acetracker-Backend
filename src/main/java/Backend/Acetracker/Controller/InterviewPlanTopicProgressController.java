package Backend.Acetracker.Controller;

import Backend.Acetracker.DTO.UpdateProgressRequest;
import Backend.Acetracker.Entity.InterviewPlanTopicProgress;
import Backend.Acetracker.Repository.InterviewPlanTopicProgressRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/interviews/{interviewId}/progress")
@CrossOrigin("http://localhost:5173")
public class InterviewPlanTopicProgressController {

    private final InterviewPlanTopicProgressRepository progressRepository;

    public InterviewPlanTopicProgressController(InterviewPlanTopicProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @GetMapping
    public ResponseEntity<List<InterviewPlanTopicProgress>> getProgress(@PathVariable Long interviewId) {
        List<InterviewPlanTopicProgress> progress = progressRepository.findByInterviewPlanId(interviewId);
        return ResponseEntity.ok(progress);
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<InterviewPlanTopicProgress> updateProgress(
            @PathVariable Long interviewId,
            @PathVariable Long topicId,
            @Valid @RequestBody UpdateProgressRequest request) {

        InterviewPlanTopicProgress progress = progressRepository
                .findByInterviewPlanIdAndTopicId(interviewId, topicId)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        progress.setProgressPercent(request.getProgressPercent());
        progress.setLastUpdatedAt(LocalDateTime.now());

        InterviewPlanTopicProgress saved = progressRepository.save(progress);
        return ResponseEntity.ok(saved);
    }
}

