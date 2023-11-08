package com.ssafy.mockstockinvestment.education;

import com.ssafy.mockstockinvestment.education.dto.EducationRegistForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Education>> getEducationList(){
        return null;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> registEducation(@RequestBody EducationRegistForm educationRegistForm){
        return ResponseEntity.ok().build();
    }
}
