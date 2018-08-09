package boot.two.demo.repository;

import boot.two.demo.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {


    Employee findByFirstName(String firstName);
    List<Employee> findByAgeGreaterThan(int age);
    List<Employee> findByAgeBetween(int age1, int age2);
}
