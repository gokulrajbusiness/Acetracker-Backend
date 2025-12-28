package Backend.Acetracker.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "interview_plan_topic_progress")
@Data
@NoArgsConstructor
public class InterviewPlanTopicProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_plan_id")
    private InterviewPlan interviewPlan;

    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "progress_percent")
    private Integer progressPercent = 0;

    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt = LocalDateTime.now();
}

