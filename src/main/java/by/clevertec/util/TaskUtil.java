package by.clevertec.util;

import by.clevertec.model.Animal;
import by.clevertec.model.CarWrapper;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.PersonWithRangeEvacuation;
import by.clevertec.model.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskUtil {

    private TaskUtil(){

    }

    private static final int DAY_IN_YEAR = 365;
    private static final double TRANSPORTATION_COST = 7.14;
    private static final int PENSION_AGE = 60;

    public static int getPensionAge() {
        return PENSION_AGE;
    }


    public static Predicate<Animal> isMale() {
        return animal -> animal.getGender().equals("Male");
    }
    public static Predicate<Animal> isFemale()  {
       return animal -> animal.getGender().equals("Female");
    }

    public static Predicate<Animal> isOrigin(String country) {
        return animal -> animal.getOrigin().equals(country);
    }

    public static Predicate<Person> isAgeOlder(int age) {
        return person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= age;
    }

    public static Predicate<Person> isAgeYounger(int age) {
        return person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <= age;
    }

    public static Function<House, Stream<PersonWithRangeEvacuation>> houseToPerson() {
        return house -> {
            boolean isInHospital = house.getBuildingType().equals("Hospital");
            return house.getPersonList().stream()
                    .map(person -> new PersonWithRangeEvacuation(person, isInHospital));
        };
    }

    public static Function<Student, Integer> mapStudentToExam(Map<Integer, Examination> examinations) throws IllegalArgumentException {
        return student -> {
           Examination examination = examinations.get(student.getId());
           if(examination == null) throw new IllegalArgumentException();

           return examination.getExam1();
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

    public static Function<Map<Country, Double>, Double> profitCalculationLogisticCompany() {
        return map -> {
            map.entrySet().forEach(System.out::println);
            return map.values().stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();
        };
    }

    public static Function<CarWrapper, Double> mappingCarInTransportationCosts() {
        return s -> s.getCar().getMass() * TRANSPORTATION_COST / 1000;
    }

    public static Predicate<Student> isMemberGroup(String group) {
        return student -> student.getGroup().equals(group);
    }

    public static Map<Integer, Examination> mappingExaminationByStudentID(List<Examination> examinations) {
        return examinations.stream()
                .collect(Collectors.toMap(
                        Examination::getStudentId,
                        examination -> examination
                ));
    }

    public static Predicate<Student> isThirdExamPassed(Map<Integer, Examination> examinations) {

        return student -> {
            Examination exam = examinations.get(student.getId());
            if (exam != null) {
                return exam.getExam3() > 4;
            } else {
                return false;
            }
        };
    }
    private Comparator<Map.Entry<String, Double>> valueComparator = (a,b) -> a.getValue().compareTo(b.getValue());

    public static Function<Map<String, Double>, String> getFacultyWithMaxAverageScore(){
        return m ->  m.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .orElseThrow().getKey();


    }

    public static Collector<Student, ?, Map<String, Double>> getCollector(Map<Integer, Examination> examinations) {
        return Collectors.groupingBy(
                Student::getFaculty,
                Collectors.mapping(
                        mapStudentToExam(examinations),
                        Collectors.averagingDouble(Integer::doubleValue)
                )
        );
    }
}
