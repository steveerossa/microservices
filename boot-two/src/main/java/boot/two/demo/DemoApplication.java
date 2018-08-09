package boot.two.demo;


import boot.two.demo.model.Student;
import boot.two.demo.repository.StudentRepository;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer implements CommandLineRunner {
    
    Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    private StudentRepository studentRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Fredrick", "Onyango", 34));
        students.add(new Student("Steve", "Onyango", 65));
        students.add(new Student("Michael", "Onyango", 233));
        students.add(new Student("Velma", "Onyango", 31));
        students.add(new Student("Dacvid", "Onyango", 34));

        log.info("///////////////////////////////////////////////////////////////");
        List<Object[]> partialData = this.studentRepository.findAllStudentsPartialData();
        partialData.stream().map(data -> data[0] + ", " + data[1]).forEach(System.out::println);

        List<Student> namedStudents = this.studentRepository.studentScoreRangeBetween(300, 400);
        this.studentRepository.deleteStudentByFirstName("Okun");
        namedStudents.forEach(System.out::println);

        List<Student> studentsList = this.studentRepository.findAllStudents(new PageRequest(1, 2));
        log.info(studentsList.toString());

        List<Student> studentsNQ = this.studentRepository.findAllStudentsNQ();
        log.info("/////////////////////////////////////////////////");
        log.info(studentsNQ.toString());
        log.info("/////////////----------------------///////////////");
        TypedQuery<Student> studentTypedQuery = this.entityManager.createNamedQuery("over300", Student.class);
        List<Student> queryStudent = studentTypedQuery.getResultList();
        Preconditions.checkNotNull(queryStudent);
        log.info(queryStudent.toString());


    }
}
