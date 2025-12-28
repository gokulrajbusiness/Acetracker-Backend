package Backend.Acetracker.Controller;

import Backend.Acetracker.DTO.CreateSkillRequest;
import Backend.Acetracker.DTO.SkillResponse;
import Backend.Acetracker.Entity.Skill;
import Backend.Acetracker.Entity.User;
import Backend.Acetracker.Repository.SkillRepository;
import Backend.Acetracker.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/skills")
@CrossOrigin("http://localhost:5173")
public class SkillController {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillController(SkillRepository skillRepository, UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<SkillResponse>> getSkills(@RequestParam Long userId) {
        List<Skill> skills = skillRepository.findByUserId(userId);
        List<SkillResponse> responses = skills.stream()
                .map(SkillResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(
            @Valid @RequestBody CreateSkillRequest request,
            @RequestParam Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Skill skill = new Skill();
        skill.setName(request.getName());
        skill.setDescription(request.getDescription());
        skill.setUser(user);

        Skill savedSkill = skillRepository.save(skill);
        return ResponseEntity.ok(savedSkill);
    }
}


