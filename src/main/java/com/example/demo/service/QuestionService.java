
package com.example.demo.service;


import com.example.demo.entity.Question;
import com.example.demo.repo.QuestionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepo questionRepo;

    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    // Get all questions
    public ResponseEntity<List<Question>> getAllQuestion() {
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity <List<Question>> getQuestionByCategory(String category){
      try{
          return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
      }catch (Exception e){
          e.printStackTrace();
      }
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity <String> addQuestion(Question question){
         try{
             questionRepo.save(question);
             return new ResponseEntity<>("Success created", HttpStatus.OK);
         }catch (Exception e){
             e.printStackTrace();
         }
         return new ResponseEntity<>("Internal servier error", HttpStatus.BAD_REQUEST);
    }

//    public ResponseEntity <Question> getQuestionById(Long id){
//        try{
//            return new ResponseEntity<>(questionRepo.findById(id), HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public ResponseEntity<Question> getQuestionById(Long id) {
        return questionRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Question> deleteQuestionById(Long id) {
        if (!questionRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        questionRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Question> updateQuestionById(
            Long id, Question updatedQuestion) {

        return questionRepo.findById(id)
                .map(existingQuestion -> {
                    existingQuestion.setQuestionTitle(
                            updatedQuestion.getQuestionTitle());
                    existingQuestion.setOption1(updatedQuestion.getOption1());
                    existingQuestion.setOption2(updatedQuestion.getOption2());
                    existingQuestion.setOption3(updatedQuestion.getOption3());
                    existingQuestion.setOption4(updatedQuestion.getOption4());
                    existingQuestion.setRightAnswer(
                            updatedQuestion.getRightAnswer());
                    existingQuestion.setDifficultyLevel(
                            updatedQuestion.getDifficultyLevel());

                    Question savedQuestion =
                            questionRepo.save(existingQuestion);

                    return ResponseEntity.ok(savedQuestion);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
