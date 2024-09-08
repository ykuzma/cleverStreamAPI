package by.clevertec;

import by.clevertec.exception.IOFileException;
import by.clevertec.model.Animal;
import by.clevertec.model.AnimalWrapper;
import by.clevertec.model.Car;
import by.clevertec.model.CarWrapper;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.PersonWithRangeEvacuation;
import by.clevertec.model.Student;
import by.clevertec.util.Country;
import by.clevertec.util.EvacuationRank;
import by.clevertec.util.FlowerComparator;
import by.clevertec.util.LogisticIndex;
import by.clevertec.util.TaskUtil;
import by.clevertec.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static by.clevertec.util.TaskUtil.getCollector;
import static by.clevertec.util.TaskUtil.getFacultyWithMaxAverageScore;
import static by.clevertec.util.TaskUtil.houseToPerson;
import static by.clevertec.util.TaskUtil.isFemale;
import static by.clevertec.util.TaskUtil.isMale;
import static by.clevertec.util.TaskUtil.isMemberGroup;
import static by.clevertec.util.TaskUtil.isThirdExamPassed;
import static by.clevertec.util.TaskUtil.isVaseMaterialSuitable;
import static by.clevertec.util.TaskUtil.mappingCarInTransportationCosts;
import static by.clevertec.util.TaskUtil.mappingExaminationByStudentID;
import static by.clevertec.util.TaskUtil.mappingFlowersInServiceCost;
import static by.clevertec.util.TaskUtil.profitCalculationLogisticCompany;

public class Main {

    public static void main(String[] args) {
        task1();
        task2(Util.getAnimals());
        task3();
        task4(Util.getAnimals());
        task5(Util.getAnimals());
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12(Util.getPersons());
        task13(Util.getHouses());
        task14(Util.getCars());
        task15(Util.getFlowers());
        task16();
        task17();
        task18();
      /*  task19(Util.getStudents(), new BufferedReader(new InputStreamReader(System.in)));*/
        task20(Util.getStudents());
        task21(Util.getStudents());
        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 9 && animal.getAge() < 21)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .map(AnimalWrapper::createAnimalWrapper)
                .filter(animal -> animal.getNumberZoo() == 3)
                .map(AnimalWrapper::getAnimal)
                .forEach(System.out::println);


    }


    public static List<String> task2(List<Animal> animals) {
        return animals.stream()
                .filter(TaskUtil.isOrigin("Japanese"))
                .peek(animal -> animal.setBread(animal.getBread().toUpperCase()))
                .filter(isFemale())
                .map(Animal::getBread)
                .peek(System.out::println)
                .toList();
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(s -> s.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    public static long task4(List<Animal> animals) {
        long count = animals.stream()
                .filter(isFemale())
                .count();
        System.out.println(count);
        return count;
    }

    public static boolean task5(List<Animal> animals) {

        return animals.stream()
                .filter(animal -> animal.getAge() > 19 && animal.getAge() < 31)
                .anyMatch(TaskUtil.isOrigin("Hungarian"));
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .allMatch(isFemale().or(isMale())));
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .noneMatch(TaskUtil.isOrigin("Oceania")));
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .map(Animal::getAge)
                .max(Comparator.naturalOrder())
                .orElseThrow());
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparing(Array::getLength))
                .orElseThrow()
                .length);
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .mapToInt(Animal::getAge)
                .sum());
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .filter(TaskUtil.isOrigin("Indonesian"))
                .mapToInt(Animal::getAge)
                .average()
                .orElseThrow());
    }

    public static List<Person> task12(List<Person> persons) {

        return persons.stream()
                .filter(person -> person.getGender().equals("Male"))
                .filter(TaskUtil.isAgeOlder(18).and(TaskUtil.isAgeYounger(27)))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .peek(System.out::println)
                .toList();
    }

    public static List<Person> task13(List<House> houses) {

        return houses.stream()
                .flatMap(houseToPerson())
                .sorted(Comparator.comparing(EvacuationRank::getRank))
                .limit(50)
                .map(PersonWithRangeEvacuation::getPerson)
                .peek(System.out::println)
                .toList();
    }

    public static double task14(List<Car> cars) {
        return cars.stream()
                .limit(300)
                .map(CarWrapper::new)
                .filter(index -> index.getIndex() < 6)
                .sorted(Comparator.comparingInt(LogisticIndex::getIndex))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        s -> Country.values()[s.getIndex()],
                                        Collectors.mapping(
                                                mappingCarInTransportationCosts(),
                                                Collectors.summingDouble(Double::doubleValue)
                                        )),
                                profitCalculationLogisticCompany()
                        )
                );
    }

    public static double task15(List<Flower> flowers) {

        return flowers.stream()
                .limit(100)
                .sorted(new FlowerComparator())
                .filter(flower -> flower.getCommonName().toCharArray()[0] >= 'C')
                .filter(flower -> flower.getCommonName().toCharArray()[0] <= 'S')
                .filter(Flower::isShadePreferred)
                .filter(isVaseMaterialSuitable())
                .mapToDouble(mappingFlowersInServiceCost(1.39, 5))
                .sum();
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(s -> s.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(n -> System.out.printf("%s - %s%n", n.getSurname(), n.getAge()));

    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream()
                .map(Student::getGroup)
                .distinct()
                .forEach(System.out::println);
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        Integer collect = students.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(
                                Student::getFaculty,
                                Collectors.averagingDouble(Student::getAge)
                        ), Map::size));
        System.out.println(collect);
    }

    public static List<Student> task19(List<Student> students, BufferedReader bufferedReader) {

        Map<Integer, Examination> examinations = mappingExaminationByStudentID(Util.getExaminations());
        try (BufferedReader br = bufferedReader) {
            return students.stream()
                    .filter(isMemberGroup(br.readLine()))
                    .filter(isThirdExamPassed(examinations))
                    .toList();
        } catch (IOException e) {
            throw new IOFileException(e.getMessage());
        }
    }

    public static String task20(List<Student> students) {

        Map<Integer, Examination> examinations = mappingExaminationByStudentID(Util.getExaminations());
        return students.stream()
                .limit(20)
                .collect(
                        Collectors.collectingAndThen(
                                getCollector(examinations),
                                getFacultyWithMaxAverageScore()
                        )
                );
    }

    public static Map<String, Long> task21(List<Student> students) {
        return students.stream()
                .collect(
                        Collectors.groupingBy(
                                Student::getGroup,
                                Collectors.counting()
                        )
                );
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        System.out.println(students.stream()
                .collect(
                        Collectors.groupingBy(
                                Student::getFaculty,
                                Collectors.mapping(
                                        Student::getAge,
                                        Collectors.minBy(Comparator.naturalOrder())
                                )
                        )
                ));
    }
}
