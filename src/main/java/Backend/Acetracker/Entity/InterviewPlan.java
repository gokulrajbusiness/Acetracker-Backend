package Backend.Acetracker.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "interview_plans")
@Data
@NoArgsConstructor
public class InterviewPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String role;

    @Column(name = "interview_date", nullable = false)
    private LocalDate interviewDate;

    @Column(name = "round_type")
    private String roundType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InterviewStatus status = InterviewStatus.UPCOMING;

    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "interviewPlan", cascade = CascadeType.ALL)
    private List<InterviewPlanTopicProgress> topicProgress;
}

