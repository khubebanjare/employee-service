package org.khube.main.controler;

import org.khube.main.dto.request.EmployeeRequestDto;
import org.khube.main.dto.response.EmployeeResponseDto;
import org.khube.main.service.EmployeeService;
import org.khube.main.service.kafka.KafkaEmployeeProducerPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final KafkaEmployeeProducerPublisher kafkaEmployeeProducerPublisher;

    @Autowired
    public EmployeeController(EmployeeService employeeService, KafkaEmployeeProducerPublisher kafkaEmployeeProducerPublisher) {
        this.employeeService = employeeService;
        this.kafkaEmployeeProducerPublisher = kafkaEmployeeProducerPublisher;
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeDto) {
        try {
            if (employeeDto == null) {
                throw new IllegalArgumentException("Employee must not be null");
            }
            EmployeeResponseDto createdEmployee = employeeService.createEmployee(employeeDto);
            if (createdEmployee != null) {
                kafkaEmployeeProducerPublisher.publishToKafkaTopic(createdEmployee);
            }
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{empId}")
    public ResponseEntity<EmployeeResponseDto> fetchEmployeeById(@PathVariable("empId") Long empId) {
        try {
            Optional<EmployeeResponseDto> optionalEmployee = employeeService.getEmployeeByEmpId(empId);
            if (optionalEmployee.isPresent()) {
                return optionalEmployee
                        .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
