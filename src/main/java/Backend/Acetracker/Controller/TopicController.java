package Backend.Acetracker.Controller;

import Backend.Acetracker.DTO.CreateTopicRequest;
import Backend.Acetracker.DTO.TopicDetailResponse;
import Backend.Acetracker.DTO.TopicResponse;
import Backend.Acetracker.DTO.UpdateTopicRequest;
import Backend.Acetracker.Entity.Skill;
import Backend.Acetracker.Entity.Topic;
import Backend.Acetracker.Repository.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{skillId}")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class TopicController {

    private final TopicRepository topicRepository;

    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping("/topics")
    public ResponseEntity<List<TopicResponse>> getTopics(@PathVariable Long skillId) {
        List<Topic> topics = topicRepository.findBySkillId(skillId);
        List<TopicResponse> responses = topics.stream()
                .map(TopicResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/topics")
    public ResponseEntity<Topic> createTopic(
            @PathVariable Long skillId,
            @Valid @RequestBody CreateTopicRequest request) {

        Topic topic = new Topic();
        topic.setName(request.getName());
        topic.setDefinition(request.getDefinition());
        topic.setTypes(request.getTypes());
        topic.setDifficultyRating(request.getDifficultyRating());
        topic.setInterviewFrequency(request.getInterviewFrequency());
        topic.setRevisionCritical(request.getRevisionCritical());
        topic.setNotes(request.getNotes());

        // Set skill relationship
        Skill skill = new Skill();
        skill.setId(skillId);
        topic.setSkill(skill);

        Topic savedTopic = topicRepository.save(topic);
        return ResponseEntity.ok(savedTopic);
    }

    @GetMapping("/topics/{topicId}")
    public ResponseEntity<TopicDetailResponse> getTopicDetail(@PathVariable Long skillId,@PathVariable Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found"));
        return ResponseEntity.ok(new TopicDetailResponse(topic));
    }

    @PutMapping("/topics/{topicId}")
    public ResponseEntity<TopicDetailResponse> updateTopic(@PathVariable Long skillId,
            @PathVariable Long topicId,
            @Valid @RequestBody UpdateTopicRequest request) {

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found"));

        topic.setName(request.getName());
        topic.setDefinition(request.getDefinition());
        topic.setTypes(request.getTypes());
        topic.setDifficultyRating(request.getDifficultyRating());
        topic.setInterviewFrequency(request.getInterviewFrequency());
        topic.setRevisionCritical(request.getRevisionCritical());
        topic.setNotes(request.getNotes());

        Topic updatedTopic = topicRepository.save(topic);
        return ResponseEntity.ok(new TopicDetailResponse(updatedTopic));
    }
}

