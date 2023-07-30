package com.pblgllgs.datajpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            studentIdCardRepository.save( new StudentIdCad(generateStudent(), "1234567890"));
            studentRepository.findById(1L).ifPresent(System.out::println);
            studentIdCardRepository.findById(1L).ifPresent(System.out::println);
        };
    }

}
