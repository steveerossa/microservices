package boot.two.demo.model;

import javax.persistence.*;


@Entity
@NamedQuery(name="over300", query="SELECT  s FROM Student s where s.score > 300")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "lname")
    private String lastName;
    @Column(name = "fname")
    private String fastName;
    private int score;

    public Student() {

    }

    public Student(String lastName, String fastName, int score) {
        this.lastName = lastName;
        this.fastName = fastName;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFastName() {
        return fastName;
    }

    public void setFastName(String fastName) {
        this.fastName = fastName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student[" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", fastName='" + fastName + '\'' +
                ", score=" + score +
                ']';
    }
}
