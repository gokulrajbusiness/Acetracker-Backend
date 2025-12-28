package Backend.Acetracker.DTO;

import Backend.Acetracker.Entity.Question;
import Backend.Acetracker.Entity.QuestionDifficulty;
import Backend.Acetracker.Entity.SourceType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuestionResponse {
    private Long id;
    private String title;
    private String prompt;
    private String answer;
    private QuestionDifficulty difficulty;
    private Integer interviewFrequencyHint;
    private SourceType sourceType;
    private String sourceRef;
    private LocalDateTime createdAt;

    public QuestionResponse(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.prompt = question.getPrompt();
        this.answer = question.getAnswer();
        this.difficulty = question.getDifficulty();
        this.interviewFrequencyHint = question.getInterviewFrequencyHint();
        this.sourceType = question.getSourceType();
        this.sourceRef = question.getSourceRef();
        this.createdAt = question.getCreatedAt();
    }
}
