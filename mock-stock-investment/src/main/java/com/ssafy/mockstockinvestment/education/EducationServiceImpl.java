package com.ssafy.mockstockinvestment.education;

import com.ssafy.mockstockinvestment.education.dto.EducationRegistForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService{
    private final EducationRepository educationRepository;

    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    /**
     * education 컨텐츠 목록 가져오기 개수 제한 걸지 않았음
     * @return
     */
    @Override
    public List<Education> getEducationList() {
        return educationRepository.findAll();
    }

    /**
     * education 컨텐츠 등록
     * @param educationRegistForm
     */
    @Override
    public void registEducation(EducationRegistForm educationRegistForm) {
        educationRepository.save(educationRegistForm.toEducation());
    }
}
