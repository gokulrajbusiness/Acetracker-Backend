package Backend.Acetracker.Controller;

import Backend.Acetracker.DTO.CreateInterviewPlanRequest;
import Backend.Acetracker.Entity.InterviewPlan;
import Backend.Acetracker.Entity.InterviewPlanTopicProgress;
import Backend.Acetracker.Entity.User;
import Backend.Acetracker.Repository.InterviewPlanRepository;
import Backend.Acetracker.Repository.InterviewPlanTopicProgressRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
@CrossOrigin("http://localhost:5173")
public class InterviewPlanController {

    private final InterviewPlanRepository interviewPlanRepository;
    private final InterviewPlanTopicProgressRepository progressRepository;

    public InterviewPlanController(InterviewPlanRepository interviewPlanRepository,
                                   InterviewPlanTopicProgressRepository progressRepository) {
        this.interviewPlanRepository = interviewPlanRepository;
        this.progressRepository = progressRepository;
    }

    @GetMapping
    public ResponseEntity<List<InterviewPlan>> getInterviews(@RequestParam Long userId) {
        return ResponseEntity.ok(interviewPlanRepository.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<InterviewPlan> createInterview(
            @RequestParam Long userId,
            @Valid @RequestBody CreateInterviewPlanRequest request) {

        // Create interview plan
        InterviewPlan plan = new InterviewPlan();
        plan.setCompanyName(request.getCompanyName());
        plan.setRole(request.getRole());
        plan.setInterviewDate(request.getInterviewDate());
        plan.setRoundType(request.getRoundType());

        // Set user
        User user = new User();
        user.setId(userId);
        plan.setUser(user);

        InterviewPlan savedPlan = interviewPlanRepository.save(plan);

        // Create progress tracking for selected topics (0% initially)
        if (request.getTopicIds() != null) {
            for (Long topicId : request.getTopicIds()) {
                InterviewPlanTopicProgress progress = new InterviewPlanTopicProgress();
                progress.setInterviewPlan(savedPlan);
                progress.setTopicId(topicId);
                progress.setProgressPercent(0);
                progressRepository.save(progress);
            }
        }

        return ResponseEntity.ok(savedPlan);
    }
}

