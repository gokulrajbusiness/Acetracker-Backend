package Backend.Acetracker.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String prompt;

    @Column(nullable = false)
    private String answer;

    @Enumerated(EnumType.STRING)
    private QuestionDifficulty difficulty;

    @Column(name = "interview_frequency_hint")
    private Integer interviewFrequencyHint;

    @Column(name = "source_type")
    @Enumerated(EnumType.STRING)
    private SourceType sourceType = SourceType.TEXT;

    @Column(name = "source_ref")
    private String sourceRef;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
}

