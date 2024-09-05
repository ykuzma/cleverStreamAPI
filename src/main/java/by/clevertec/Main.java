package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.AnimalWrapper;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.PersonWithRangeEvacuation;
import by.clevertec.model.Student;
import by.clevertec.util.EvacuationRank;
import by.clevertec.util.TaskUtil;
import by.clevertec.util.Util;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
      /*  task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();*/
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
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


    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(TaskUtil.isOrigin("Japanese"))
                .peek(animal -> animal.setBread(animal.getBread().toUpperCase()))
                .forEach(System.out::println);
//        Отобрать всех животных из Японии (Japanese)
//        и записать породу UPPER_CASE в если пол Female
//        преобразовать к строкам породы животных и вывести в консоль
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(s -> s.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .filter(TaskUtil.isFemale)
                .count());
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .filter(animal -> animal.getAge() > 19 && animal.getAge() < 31)
                .anyMatch(TaskUtil.isOrigin("Hungarian")));
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .allMatch(TaskUtil.isFemale.or(TaskUtil.isMale)));
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
                .reduce(Math::max)
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

    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> !person.getGender().equals("Female"))
                .filter(TaskUtil.isAgeOlder(18).and(TaskUtil.isAgeYounger(27)))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    public static void task13() {
        List<House> houses = Util.getHouses();
        houses.stream()
                .flatMap(TaskUtil.houseToPerson)
                .sorted(Comparator.comparing(EvacuationRank::getRank))
                .limit(50)
                .map(PersonWithRangeEvacuation::getPerson)
                .forEach(System.out::println);
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
//        cars.stream() Продолжить ...
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
//        flowers.stream() Продолжить ...
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
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

    public static void task19() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }
}
