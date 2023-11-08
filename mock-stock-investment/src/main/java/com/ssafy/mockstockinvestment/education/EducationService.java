package com.ssafy.mockstockinvestment.education;

import com.ssafy.mockstockinvestment.education.dto.EducationRegistForm;

import java.util.List;

public interface EducationService {
    List<Education> getEducationList();
    void registEducation(EducationRegistForm educationRegistForm);
    Education getEducation(Long id);
    void deleteEducation(Long id);
}
