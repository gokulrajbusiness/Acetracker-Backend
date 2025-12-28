package Backend.Acetracker.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateTopicRequest {
    @NotBlank
    private String name;
    private String definition;
    private String types;
    @Min(1) @Max(5) private Integer difficultyRating;
    @Min(1) @Max(5) private Integer interviewFrequency;
    private Boolean revisionCritical;
    private String notes;
}

