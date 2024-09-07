package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.reader.JsonReader;
import by.clevertec.util.reader.Reader;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest {
    private static final String TEST_TASK2 = "src/test/resources/json/TestTask2.json";
    private static final String RECRUITS_DATA_FILE = "src/test/resources/json/recruits.json";
    private static final String CARS_DATA_FILE = "src/test/resources/json/cars.json";
    private static final String FLOWERS_DATA_FILE = "src/test/resources/json/flowers.json";
    private static final String STUDENTS_DATA_FILE = "src/test/resources/json/students.json";
    private static final String EXAMINATION_DATA_FILE = "src/test/resources/json/examinations.json";
    private static final String BUILDING_TYPE_HOSPITAL = "Hospital";
    private static final String BUILDING_TYPE_OTHER = "Civil building";

    private static final Reader reader = new JsonReader();

    public static List<List<Animal>> getAnimals() {
        List<Animal> animals = reader.getModelData(TEST_TASK2, new TypeReference<>() {
        });
        return List.of(
                new ArrayList<>(animals.subList(0,3)),
                new ArrayList<>(animals.subList(3,6)),
                new ArrayList<>(animals.subList(0, 9))
        );
    }

    public static List<Person> getPersons() {
        return reader.getModelData(RECRUITS_DATA_FILE, new TypeReference<>() {
        });
    }

    public static List<Car> getCars() {
        return reader.getModelData(CARS_DATA_FILE, new TypeReference<>() {
        });
    }

    public static List<Flower> getFlowers() {
        return reader.getModelData(FLOWERS_DATA_FILE, new TypeReference<>() {
        });
    }

    public static List<House> getHouses() {
        List<Person> personList = getPersons();

        return List.of(
                new House(1, BUILDING_TYPE_HOSPITAL, personList.subList(0, 40)),
                new House(2, BUILDING_TYPE_OTHER, personList.subList(41, 141)),
                new House(3, BUILDING_TYPE_OTHER, personList.subList(142, 200)),
                new House(4, BUILDING_TYPE_OTHER, personList.subList(201, 299))
               /* new House(5, BUILDING_TYPE_OTHER, personList.subList(300, 399)),
                new House(6, BUILDING_TYPE_OTHER, personList.subList(400, 499)),
                new House(7, BUILDING_TYPE_OTHER, personList.subList(500, 599)),
                new House(8, BUILDING_TYPE_OTHER, personList.subList(600, 699)),
                new House(9, BUILDING_TYPE_OTHER, personList.subList(700, 799)),
                new House(10, BUILDING_TYPE_OTHER, personList.subList(800, 899)),
                new House(11, BUILDING_TYPE_OTHER, personList.subList(900, 999))*/
        );
    }

    public static List<Student> getStudents() {
        return reader.getModelData(STUDENTS_DATA_FILE, new TypeReference<>() {
        });
    }

    public static List<Examination> getExaminations() {
        return reader.getModelData(EXAMINATION_DATA_FILE, new TypeReference<>() {
        });
    }
}

