package com.pblgllgs.datajpa;

import com.github.javafaker.Faker;
import com.pblgllgs.datajpa.entity.Book;
import com.pblgllgs.datajpa.entity.Student;
import com.pblgllgs.datajpa.entity.StudentIdCad;
import com.pblgllgs.datajpa.repository.StudentIdCardRepository;
import com.pblgllgs.datajpa.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }

    private static void generateRandomStudents(StudentRepository studentRepository) {
        for (int i = 0; i < 20; i++) {
            Student student = generateStudent();
            studentRepository.save(student);
        }
    }

    private static Student generateStudent() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = String.format("%s.%s@gmail.com", firstName, lastName);
        return new Student(firstName, lastName, email, faker.number().numberBetween(17, 55));
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository) {
        return args -> {
            Student student = generateStudent();
            student.addBook(new Book(
                    "Clean Code",
                    LocalDateTime.now().minusDays(4)
            ));

            student.addBook(new Book(
                    "Spring Boot 3",
                    LocalDateTime.now().minusDays(4)
            ));

            student.addBook(new Book(
                    "Amazon Web Services",
                    LocalDateTime.now().minusDays(4)
            ));
            StudentIdCad studentIdCad = new StudentIdCad(student, "1234567890");
            studentIdCardRepository.save(studentIdCad);

            studentRepository.findById(1L).ifPresent( s ->{
                System.out.println("fetch books lazy...");
                List<Book> books = student.getBooks();
                books.forEach( book-> System.out.println(
                        s.getFirstName() + " borrowed "+ book.getBookName()));
            });
        };
    }

}
