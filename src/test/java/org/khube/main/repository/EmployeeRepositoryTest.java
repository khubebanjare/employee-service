//package org.khube.main.repository;
//
//import org.junit.jupiter.api.Test;
//import org.khube.main.entity.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class EmployeeRepositoryTest {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Test
//    void testCreateEmployee() {
//        Employee employee = new Employee(null, "Khube", "Banjare", 30, 50000.0);
//
//        Employee result = employeeRepository.save(employee);
//
//        assertNotNull(result);
//        assertEquals("Khube", result.getFirstName());
//        assertEquals("Banjare", result.getLastName());
//        assertEquals(30, result.getAge());
//        assertEquals(50000.0, result.getSal());
//    }
//
//    @Test
//    void testFindEmployeeById() {
//        Employee employee = new Employee(null, "Khube", "Banjare", 30, 50000.0);
//
//        Employee createdEmployee = employeeRepository.save(employee);
//
//        Optional<Employee> foundEmployee = employeeRepository.findById(createdEmployee.getEmpId());
//
//        assertTrue(foundEmployee.isPresent());
//        assertEquals("Khube", foundEmployee.get().getFirstName());
//        assertEquals("Banjare", foundEmployee.get().getLastName());
//        assertEquals(30, foundEmployee.get().getAge());
//        assertEquals(50000.0, foundEmployee.get().getSal());
//    }
//}
