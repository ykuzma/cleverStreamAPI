package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.reader.JsonReader;
import by.clevertec.util.reader.Reader;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class UtilsTest {
    private static final String ANIMALS_TEST = "src/test/resources/json/AnimalsTest.json";
    private static final String RECRUITS_TEST = "src/test/resources/json/recruitsTest.json";
    private static final String CARS_DATA_FILE = "src/test/resources/json/carsTest.json";
    private static final String FLOWERS_DATA_FILE = "src/test/resources/json/flowersTest.json";
    private static final String STUDENTS_DATA_FILE = "src/test/resources/json/studentsTest.json";
    private static final String EXAMINATION_DATA_FILE = "src/test/resources/json/examinations.json";

    private static final Reader reader = new JsonReader();

    public static List<List<Animal>> getAnimals() {
        List<Animal> animals = reader.getModelData(ANIMALS_TEST, new TypeReference<>() {
        });
        return List.of(
                animals.subList(0,3),
                animals.subList(3,6),
                animals.subList(0, 9),
                animals.subList(0, 2),
                animals.subList(9,12),
                animals.subList(12,15),
                animals.subList(9, 17),
                animals.subList(17,20)
        );
    }

    public static List<Person> getPersons() {
        return reader.getModelData(RECRUITS_TEST, new TypeReference<>() {
        });

    }

    public static List<List<Car>> getCars() {
        List<Car> cars = reader.getModelData(CARS_DATA_FILE, new TypeReference<>() {
        });
        return List.of(
                cars.subList(0,7),
                cars.subList(6,7),
                cars.subList(0,1)

        );
    }

    public static List<List<Flower>> getFlowers() {
        List<Flower> flowers = reader.getModelData(FLOWERS_DATA_FILE, new TypeReference<>() {
        });

        return List.of(
                flowers.subList(0,3),
                flowers.subList(3,6),
                flowers.subList(6,9),
                flowers.subList(9,12),
                flowers.subList(12,15),
                flowers.subList(15,18),
                flowers.subList(18,21),
                flowers.subList(21,24),
                flowers.subList(24,27),
                flowers.subList(27,30)
        );
    }

    public static List<List<Student>> getStudents() {
        List<Student> students = reader.getModelData(STUDENTS_DATA_FILE, new TypeReference<>() {
        });
        return List.of(
                students.subList(0, 6),
                students.subList(0, 25)
        );
    }

    public static List<Examination> getExaminations() {
        return reader.getModelData(EXAMINATION_DATA_FILE, new TypeReference<>() {
        });
    }
}

