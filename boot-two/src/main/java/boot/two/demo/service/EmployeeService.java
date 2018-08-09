package boot.two.demo.service;

import boot.two.demo.model.Employee;
import boot.two.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        this.repository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    public void createEmployee(Employee employee) {
        this.repository.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        List<Employee> employees = namedParameterJdbcTemplate.query("Select * from Employee", (resultSet, i) -> {
            Employee employee = new Employee();
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setId(resultSet.getLong("id"));
            return employee;
        });

        System.out.println(employees);
        return this.repository.findById(id).isPresent() ? repository.findById(id).get() : new Employee();
    }

    public Employee findByFirstName(String firstName) {
        Pageable pageable = PageRequest.of(2, 2);
        Page<Employee> employeePage = repository.findAll(pageable);
        for (Employee employee : employeePage) {
            System.out.println("--> "  + employee.getFirstName());
        }
        return this.repository.findByFirstName(firstName);

    }

    public List<Employee> findByAgeGreaterThan() {
        return repository.findByAgeGreaterThan(50);
    }

    public List<Employee> findByAgeBetween(int age1, int age2) {
        this.repository.findAll(new Sort(Sort.Direction.DESC, "age")).forEach(System.out::println);
        return repository.findByAgeBetween(age1, age2);
    }

}
