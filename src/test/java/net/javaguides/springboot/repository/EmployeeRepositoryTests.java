package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest            // Provides and configures the in-memory database (h2) for the tests
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();

        // when - action or behavior we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for a get all employees operation")
    @Test
    public void givenEmployeeList_whenFindAll_thenEmployeeList() {

        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .firstName("John")
                .lastName("Cena")
                .email("john@gmail.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Fred")
                .lastName("Astaire")
                .email("fred@gmail.com").build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        // when - action or behavior to be tested
        List<Employee> list =employeeRepository.findAll();

        // then - verify output
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for a get employee by Id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Cena")
                .email("john@gmail.com")
                .build();
        employeeRepository.save(employee);

        // when - action or behavior to be tested
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();

        // then - verify output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for get employee by email operation
    @DisplayName("JUnit test for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Cena")
                .email("john@gmail.com")
                .build();
        employeeRepository.save(employee);

        // when - action or behavior to be tested
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify output
        assertThat(employeeDB).isNotNull();
    }
}
