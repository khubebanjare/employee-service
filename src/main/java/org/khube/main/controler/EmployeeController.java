package org.khube.main.controler;

import org.khube.main.dto.request.EmployeeRequestDto;
import org.khube.main.dto.response.EmployeeResponseDto;
import org.khube.main.entity.Employee;
import org.khube.main.service.EmployeeService;
import org.khube.main.service.kafka.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final KafkaMessagePublisher kafkaMessagePublisher;

    @Autowired
    public EmployeeController(EmployeeService employeeService, KafkaMessagePublisher kafkaMessagePublisher) {
        this.employeeService = employeeService;
        this.kafkaMessagePublisher = kafkaMessagePublisher;
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeDto) {
        try {
            if (employeeDto == null) {
                throw new IllegalArgumentException("Employee must not be null");
            }
            kafkaMessagePublisher.publishToKafkaTopic(employeeDto.toString());
            return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
