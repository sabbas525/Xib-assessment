package com.xib.assessment.service.managerservice;

import com.xib.assessment.constants.AppConstants;
import com.xib.assessment.mapper.managermapper.ManagerDTO;
import com.xib.assessment.mapper.managermapper.ManagerMapper;
import com.xib.assessment.model.APIResponse;
import com.xib.assessment.model.Manager;
import com.xib.assessment.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public APIResponse createManager(ManagerDTO managerDTO) {
        try {
            Manager manager = ManagerMapper.toManager(managerDTO);
            manager = managerRepository.save(manager);
            return buildSuccessResponse(ManagerMapper.toManagerDTO(manager));
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public APIResponse findAllManagers() {
        List<ManagerDTO> managers = ManagerMapper.toManagerDTOList(managerRepository.findAll());
        return buildSuccessResponse(managers);
    }

    /**
     * Builds a successful API response.
     *
     * @param data the data to be included in the response
     * @return APIResponse object with success information
     */
    private APIResponse buildSuccessResponse(Object data) {
        return APIResponse.builder().code(HttpStatus.OK.value()).message(AppConstants.HTTP_MESSAGES.SUCCESS).data(data).build();
    }

    /**
     * Builds an error API response.
     *
     * @param errorMessage the error message
     * @param status       the HTTP status for the error
     * @return APIResponse object with error information
     */
    private APIResponse buildErrorResponse(String errorMessage, HttpStatus status) {
        return APIResponse.builder().code(status.value()).error(errorMessage).build();
    }
}
