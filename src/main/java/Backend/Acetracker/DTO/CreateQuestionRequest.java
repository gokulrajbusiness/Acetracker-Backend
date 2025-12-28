package Backend.Acetracker.DTO;


import Backend.Acetracker.Entity.QuestionDifficulty;
import Backend.Acetracker.Entity.SourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateQuestionRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String prompt;

    @NotBlank
    private String answer;

    @NotNull
    private QuestionDifficulty difficulty;

    private Integer interviewFrequencyHint;

    private SourceType sourceType = SourceType.TEXT;

    private String sourceRef;
}

