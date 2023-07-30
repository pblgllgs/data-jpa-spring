package com.pblgllgs.datajpa.repository;

import com.pblgllgs.datajpa.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository  extends PagingAndSortingRepository<Student,Long> {
    Optional<Student> findStudentByEmail(String email);
    List<Student> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);
    List<Student> findStudentsByFirstNameEqualsAndAgeGreaterThan(String firstName, Integer age);
    @Query("SELECT s FROM Student s WHERE s.email=?1")
    Student findStudentByEmailJPQL(String email);
    @Query("SELECT s FROM Student s WHERE s.firstName=?1 and s.age=?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeEqualsJPQL(String firstName, Integer age);
    @Query("SELECT s FROM Student s WHERE s.lastName=?1 and s.age>?2")
    List<Student> findStudentsByLastNameEqualsAndAgeGreaterThanJPQL(String lastName, Integer age);

    @Query(value = "SELECT * FROM Student WHERE last_name=:lastName and age>:age", nativeQuery = true)
    List<Student> findStudentsByLastNameEqualsAndAgeGreaterThanNative(
            @Param("lastName") String lastName,
            @Param("age") Integer age
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.id=?1")
    int deleteStudentBy(Long id);
}
