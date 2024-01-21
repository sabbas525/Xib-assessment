package com.xib.assessment.service.managerservice;

import com.xib.assessment.mapper.managermapper.ManagerDTO;
import com.xib.assessment.model.APIResponse;
/**
 * Service interface for Manager-related operations.
 */
public interface ManagerService {
    /**
     * Creates a new manager.
     *
     * @param managerDTO the data transfer object containing manager details
     * @return an APIResponse confirming the creation of the manager
     */
    APIResponse createManager(ManagerDTO managerDTO);
    /**
     * Retrieves all managers
     *
     * @return an APIResponse containing a list of agents
     */
    APIResponse findAllManagers();
}
