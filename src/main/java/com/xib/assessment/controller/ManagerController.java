package com.xib.assessment.controller;


import com.xib.assessment.mapper.managermapper.ManagerDTO;
import com.xib.assessment.service.managerservice.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * Creates a new manager based on the provided details.
     *
     * @param managerDTO the manager data transfer object containing the manager's information
     * @return a ResponseEntity indicating the result of the operation
     */
    @PostMapping
    public ResponseEntity<?> createManager(@RequestBody ManagerDTO managerDTO) {
        return ResponseEntity.ok(managerService.createManager(managerDTO));
    }

    /**
     * Retrieves a list of all managers
     *
     * @return a ResponseEntity containing the list of agents
     */
    @GetMapping
    public ResponseEntity<?> getAllManagers() {
        return ResponseEntity.ok(managerService.findAllManagers());
    }
}
