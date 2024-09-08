package by.clevertec.util;

import by.clevertec.model.Animal;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.PersonWithRangeEvacuation;
import by.clevertec.model.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskUtil {

    private static final int DAY_IN_YEAR = 365;
    private static int PENSION_AGE = 60;

    public static int getPensionAge(){
        return PENSION_AGE;
    }

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

    public static Predicate<Flower> isVaseMaterialSuitable() {
        return flower -> flower.getFlowerVaseMaterial().stream()
                .anyMatch(s -> Set.of("Aluminum", "Steel", "Glass").contains(s));
    }

    public static ToDoubleFunction<Flower> mappingFlowersInServiceCost(double costWater, int years) {
        return flower -> flower.getPrice() + costOfService(costWater, years);
    }

    private static double costOfService(double costWater, int years) {
        return DAY_IN_YEAR * years * costWater / 1000;
    }

    public static Function<Map<Country, Double>, Double> profitCalculationLogisticCompany(){
        return map -> {
            map.entrySet().forEach(System.out::println);
            return map.values().stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();
        };
    }
}
