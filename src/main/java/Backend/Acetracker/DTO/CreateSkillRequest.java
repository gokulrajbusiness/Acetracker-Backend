package Backend.Acetracker.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateSkillRequest {

    @NotBlank(message = "Skill name is required")
    @Size(max = 100, message = "Skill name too long")
    private String name;

    @Size(max = 500, message = "Description too long")
    private String description;
}
