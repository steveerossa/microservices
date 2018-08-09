package boot.two.demo.repository;

import boot.two.demo.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("from Student")
    List<Student> findAllStudents(Pageable pageable);

    @Query("select st.fastName, st.lastName from Student st")
    List<Object[]> findAllStudentsPartialData();

    @Query("from Student st where st.fastName=:firstName")
    List<Student> findAllStudentsByFastName(@Param("firstName") String firstName);

    @Query("from Student st where st.score between :firstScore and :secondScore")
    List<Student> studentScoreRangeBetween(@Param("firstScore") int firstScore,
                                           @Param("secondScore") int secondScore);
    @Modifying
    @Query("delete from Student st where st.fastName=:firstName")
    void deleteStudentByFirstName(@Param("firstName") String firstName);

    @Query(value = "SELECT * FROM STUDENT", nativeQuery = true)
    List<Student> findAllStudentsNQ();
}
