package org.khube.main.controler;

import jakarta.validation.Valid;
import org.khube.main.dto.EmployeeCreateDto;
import org.khube.main.dto.EmployeeDto;
import org.khube.main.service.EmployeeService;
import org.khube.main.service.kafka.KafkaEmployeeProducerPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final KafkaEmployeeProducerPublisher kafkaEmployeeProducerPublisher;

    @Autowired
    public EmployeeController(EmployeeService employeeService, KafkaEmployeeProducerPublisher kafkaEmployeeProducerPublisher) {
        this.employeeService = employeeService;
        this.kafkaEmployeeProducerPublisher = kafkaEmployeeProducerPublisher;
    }

    /**
     * Creates a new employee and publishes the employee data to Kafka.
     *
     * @param employeeDto the employee data to be created
     * @return ResponseEntity with the created EmployeeDto and HTTP status
     */
    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeCreateDto employeeDto) {
        try {
            if (employeeDto == null) {
                throw new IllegalArgumentException("Employee must not be null");
            }
            EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
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

    @GetMapping("/fetch/{empId}")
    public ResponseEntity<EmployeeDto> fetchEmployeeById(@PathVariable("empId") Long empId) {
        try {
            Optional<EmployeeDto> optionalEmployee = employeeService.getEmployeeByEmpId(empId);
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
