package Backend.Acetracker.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topics")
@Data
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    private String definition;
    private String types;

    @Column(name = "difficulty_rating")
    private Integer difficultyRating;

    @Column(name = "interview_frequency")
    private Integer interviewFrequency;

    @Column(name = "is_revision_critical")
    private Boolean revisionCritical = false;

    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Question> questions;
}

