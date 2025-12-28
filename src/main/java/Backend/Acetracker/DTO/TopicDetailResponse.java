package Backend.Acetracker.DTO;

import Backend.Acetracker.Entity.Topic;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopicDetailResponse {
    private Long id;
    private String name;
    private String definition;
    private String types;
    private Integer difficultyRating;
    private Integer interviewFrequency;
    private Boolean revisionCritical;
    private String notes;
    private LocalDateTime createdAt;

    public TopicDetailResponse(Topic topic) {
        this.id = topic.getId();
        this.name = topic.getName();
        this.definition = topic.getDefinition();
        this.types = topic.getTypes();
        this.difficultyRating = topic.getDifficultyRating();
        this.interviewFrequency = topic.getInterviewFrequency();
        this.revisionCritical = topic.getRevisionCritical();
        this.notes = topic.getNotes();
        this.createdAt = topic.getCreatedAt();
    }
}

