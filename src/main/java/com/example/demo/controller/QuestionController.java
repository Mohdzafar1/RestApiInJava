
package com.example.demo.controller;

import com.example.demo.entity.Question;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

//    private final QuestionService questionService;

   @Autowired
   QuestionService questionService;


    // Get all questions
    @GetMapping("/allQuestion")
    public ResponseEntity <List<Question>> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @GetMapping("category/{category}")
    public ResponseEntity <List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity <String> addQuestion(@RequestBody Question question){
     return questionService.addQuestion(question);
    }

    @GetMapping("{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) {

        return questionService.getQuestionById(questionId);
    }

    @DeleteMapping("{questionId}")
    public ResponseEntity <Question> deleteQuestionById(@PathVariable Long questionId){
        return questionService.deleteQuestionById(questionId);
    }

    @PutMapping("{questionId}")
    public ResponseEntity<Question> updateQuestionById(@PathVariable Long questionId, @RequestBody Question question) {
        return questionService.updateQuestionById(questionId, question);
    }



    // Get question by ID

}
