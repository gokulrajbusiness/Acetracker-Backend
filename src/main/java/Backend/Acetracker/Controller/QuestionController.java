package Backend.Acetracker.Controller;

import Backend.Acetracker.DTO.CreateQuestionRequest;
import Backend.Acetracker.DTO.QuestionResponse;
import Backend.Acetracker.Entity.Question;
import Backend.Acetracker.Entity.Topic;
import Backend.Acetracker.Repository.QuestionRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{topicId}/questions")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class QuestionController {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> getQuestions(@PathVariable Long topicId) {
        List<Question> questions = questionRepository.findByTopicId(topicId);
        List<QuestionResponse> responses = questions.stream()
                .map(QuestionResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(
            @PathVariable Long topicId,
            @Valid @RequestBody CreateQuestionRequest request) {

        Question question = new Question();
        question.setTitle(request.getTitle());
        question.setPrompt(request.getPrompt());
        question.setAnswer(request.getAnswer());
        question.setDifficulty(request.getDifficulty());
        question.setInterviewFrequencyHint(request.getInterviewFrequencyHint());
        question.setSourceType(request.getSourceType());
        question.setSourceRef(request.getSourceRef());

        Topic topic = new Topic();
        topic.setId(topicId);
        question.setTopic(topic);

        Question savedQuestion = questionRepository.save(question);
        return ResponseEntity.ok(new QuestionResponse(savedQuestion));
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        questionRepository.deleteById(questionId);
        return ResponseEntity.noContent().build();
    }
}


