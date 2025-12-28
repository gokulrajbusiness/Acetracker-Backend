package Backend.Acetracker.DTO;

import Backend.Acetracker.Entity.Skill;
import lombok.Data;

import java.time.LocalDateTime;

// SkillResponse DTO
@Data
public class SkillResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    public SkillResponse(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
        this.description = skill.getDescription();
        this.createdAt = skill.getCreatedAt();
    }
}

