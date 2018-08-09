package boot.two.demo.controller;

import boot.two.demo.model.Employee;
import boot.two.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/get-all-employees")
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @PostMapping(path = "/create-employee")
    public void createEmployee(@RequestBody Employee employee) {
        Employee newEmployee = new Employee();
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setFirstName(employee.getFirstName());
        this.employeeService.createEmployee(newEmployee);
    }

    @GetMapping(value = {"/get-employee/{id}"})
    public Employee findOne(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }
    @GetMapping(value = "/get-employee", params = {"name"})
    public Employee findByFirstName(@RequestParam String name) {
        return employeeService.findByFirstName(name);

    }

    @GetMapping(value = "/over-fifty")
    public List<Employee> findByAgeGreaterThan() {
        return employeeService.findByAgeGreaterThan();

    }

    @GetMapping(value = "/age-between/{age1}and{age2}")
    public List<Employee> findByAgeBetween(@PathVariable int age1, @PathVariable int age2) {
        return employeeService.findByAgeBetween(age1, age2);

    }

    // LIKE %XX%



}
