package Backend.Acetracker.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateInterviewPlanRequest {

    @NotBlank
    private String companyName;

    @NotBlank
    private String role;

    @NotNull
    private LocalDate interviewDate;

    private String roundType;

    private List<Long> topicIds; // Topics to track for this interview
}

