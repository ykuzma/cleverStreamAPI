package by.clevertec.util;

import by.clevertec.model.Animal;
import by.clevertec.model.Examination;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.PersonWithRangeEvacuation;
import by.clevertec.model.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskUtil {

    public static Supplier<String> orElse = () -> "Empty Stream";

    public static Predicate<Animal> isMale = animal -> animal.getGender().equals("Male");
    public static Predicate<Animal> isFemale = animal -> animal.getGender().equals("Female");

    public static Predicate<Animal> isOrigin(String country) {
        return animal -> animal.getOrigin().equals(country);
    }

    public static Predicate<Person> isAgeOlder(int age) {
        return person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= age;
    }

    public static Predicate<Person> isAgeYounger(int age) {
        return person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <= age;
    }

    public static Function<House, Stream<PersonWithRangeEvacuation>> houseToPerson = house -> {
        boolean isInHospital = house.getBuildingType().equals("Hospital");
        return house.getPersonList().stream()
                .map(person -> new PersonWithRangeEvacuation(person, isInHospital));
    };

    public static Function<Student, Integer> mapStudentToExam(List<Examination> examinations) {
        return student -> {
            Map<Integer, Integer> examinationsMap = examinations.stream()
                    .collect(Collectors.toMap(
                            Examination::getStudentId,
                            Examination::getExam1
                    ));
            return examinationsMap.get(student.getId());
        };
    }



}
