package Backend.Acetracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTopicRequest {

    @NotBlank(message = "Topic name is required")
    @Size(max = 100)
    private String name;

    @Size(max = 2000)
    private String definition;

    @Size(max = 2000)
    private String types;

    @Min(1) @Max(5)
    private Integer difficultyRating;

    @Min(1) @Max(5)
    private Integer interviewFrequency;

    private Boolean revisionCritical = false;

    @Size(max = 1000)
    private String notes;
}

