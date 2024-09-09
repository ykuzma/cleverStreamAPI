package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.AnimalWrapper;
import by.clevertec.model.Car;
import by.clevertec.model.CarWrapper;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.PersonWithRangeEvacuation;
import by.clevertec.model.Student;
import by.clevertec.util.EvacuationRank;
import by.clevertec.util.FlowerComparator;
import by.clevertec.util.TaskUtil;
import by.clevertec.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static by.clevertec.Main.task1;
import static by.clevertec.Main.task12;
import static by.clevertec.Main.task13;
import static by.clevertec.Main.task14;
import static by.clevertec.Main.task15;
import static by.clevertec.Main.task19;
import static by.clevertec.Main.task2;
import static by.clevertec.Main.task20;
import static by.clevertec.Main.task21;
import static by.clevertec.Main.task4;
import static by.clevertec.Main.task5;
import static by.clevertec.util.TaskUtil.getCollector;
import static by.clevertec.util.TaskUtil.mappingExaminationByStudentID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;


class MainTest {
    @Nested
    class Task1 {

        @BeforeEach
        void resetCounter(){
            AnimalWrapper.setCapacityZoo(7);
            AnimalWrapper.setCounter(1);
        }

        @ParameterizedTest
        @MethodSource("provideAnimalsAndExpectedSize")
        void GivenAnimals_WhenGetAnimalsFromThirdZoo_ThenSizeEqualsExpected(List<Animal> animals, int sizeExpected){

            List<Animal> actual = task1(animals);

            assertThat(actual).hasSize(sizeExpected);
        }

        @ParameterizedTest
        @MethodSource("provideAnimals")
        void GivenAnimals_WhenGetAnimalsFromThirdZoo_ThenAllAgeBetween10And20(List<Animal> animals){

            List<Animal> actual = task1(animals);

            assertThat(actual).filteredOn(animal -> animal.getAge() > 9)
                    .filteredOn(animal -> animal.getAge() < 21)
                    .isEqualTo(actual);
        }

        private static Stream<Arguments> provideAnimalsAndExpectedSize() {
            List<List<Animal>> animals = UtilsTest.getAnimals();
            return Stream.of(
                    Arguments.of(animals.get(0), 0),
                    Arguments.of(animals.get(8), 3),
                    Arguments.of(animals.get(9), 7)
            );
        }

        private static Stream<Arguments> provideAnimals() {
            List<List<Animal>> animals = UtilsTest.getAnimals();
            return Stream.of(
                    Arguments.of(animals.get(0)),
                    Arguments.of(animals.get(8)),
                    Arguments.of(animals.get(9))
            );
        }

    }

    @Nested
    class Task2 {
        @ParameterizedTest
        @MethodSource("provideTestTask2StartedAndExpectedList")
        void testTask2_resultListEqualsExpected(List<Animal> animals, List<String> expected) {

            List<String> result = task2(animals);

            assertThat(result).isEqualTo(expected);

        }

        @Test
        void testTask2_resultListSizeEqualsExpected() {
            List<Animal> animals = UtilsTest.getAnimals().get(2);

            List<String> result = task2(animals);

            assertThat(result).hasSize(2);
        }

        @Test
        void testTask2_allElementsIsUpperCase() {
            List<Animal> animals = UtilsTest.getAnimals().get(2);

            List<String> result = task2(animals);

            assertThat(result.stream()
                    .allMatch(s -> s.equals(s.toUpperCase()))).isTrue();
        }

        private static Stream<Arguments> provideTestTask2StartedAndExpectedList() {
            List<List<Animal>> animals = UtilsTest.getAnimals();
            return Stream.of(
                    Arguments.of(animals.get(0), List.of("JF")),
                    Arguments.of(animals.get(1), List.of()),
                    Arguments.of(animals.get(2), List.of("JF", "JF"))
            );
        }
    }

    @Nested
    class Task4 {
        @ParameterizedTest
        @MethodSource("provideStartListAndExpectedCount")
        void testTask4_countEqualsExpected (List < Animal > animals,long expected){

        Long count = task4(animals);

        assertThat(count).isEqualTo(expected);
    }

        private static Stream<Arguments> provideStartListAndExpectedCount () {
        List<List<Animal>> animals = UtilsTest.getAnimals();
        return Stream.of(
                Arguments.of(animals.get(0), 1),
                Arguments.of(animals.get(1), 1),
                Arguments.of(animals.get(2), 4),
                Arguments.of(animals.get(3), 0)
        );
    }
    }

    @Nested
    class Task5{
        @ParameterizedTest
        @MethodSource
        void GivenAnimals_WhenUsedTask5_ThenEqualsExpected (List < Animal > animals,boolean expected){
        boolean isHungarian = task5(animals);

        assertThat(isHungarian).isEqualTo(expected);
    }

        private static Stream<Arguments> GivenAnimals_WhenUsedTask5_ThenEqualsExpected () {
        List<List<Animal>> animals = UtilsTest.getAnimals();
        return Stream.of(
                Arguments.of(animals.get(4), false),
                Arguments.of(animals.get(5), false),
                Arguments.of(animals.get(6), true),
                Arguments.of(animals.get(7), true)

        );
    }
    }

    @Nested
    class Task12 {
        @ParameterizedTest
        @MethodSource("providePersons")
        void GivenPersons_WhenFiltered_ThenListContainsOnlyMale(List<Person> persons) {

            List<Person> actualPersons = task12(persons);

            assertThat(actualPersons)
                    .filteredOn(person -> person.getGender().equals("Male"))
                    .isEqualTo(actualPersons);

        }

        @ParameterizedTest
        @MethodSource("providePersons")
        void GivenPersons_WhenFiltered_ThenListContainsOnlyAgeBetween18and27(List<Person> persons) {

            List<Person> actualPersons = task12(persons);

            assertThat(actualPersons)
                    .filteredOn(TaskUtil.isAgeYounger(27).and(TaskUtil.isAgeOlder(18)))
                    .isEqualTo(actualPersons);

        }

        private static Stream<Arguments> providePersons() {
            List<Person> persons = UtilsTest.getPersons();
            return Stream.of(
                    Arguments.of(persons.subList(3, 6), 0),
                    Arguments.of(persons.subList(6, 9), 0),
                    Arguments.of(persons.subList(9, 12), 0),
                    Arguments.of(persons.subList(3, 15), 3)
            );
        }
    }

    @Nested
    class Task13 {
        @Test
        void testTask13_GivenListHouses_WhenUsedStream_ThenSortedListPerson() {
            List<Person> persons = UtilsTest.getPersons();
            List<House> houses = List.of(
                    new House(1, "Civil building", persons.subList(0, 1)),
                    new House(2, "Civil building", persons.subList(1, 2)),
                    new House(3, "Hospital", persons.subList(2, 3))
            );
            List<Person> expected = List.of(
                    persons.get(2),
                    persons.get(1),
                    persons.get(0)
            );

            List<Person> actual = task13(houses);

            assertThat(actual).isEqualTo(expected);
        }

        @ParameterizedTest
        @MethodSource("provideTestTask13EvacuationRankAndExpectedValue")
        void testTask13_GivenEvacuationRank_WhenGetRank_ThenIntEqualsExpected(EvacuationRank wrapper, int expected) {

            int actual = wrapper.getRank();

            assertThat(actual).isEqualTo(expected);
        }

        private static Stream<Arguments> provideTestTask13EvacuationRankAndExpectedValue() {
            List<Person> persons = UtilsTest.getPersons();
            return Stream.of(
                    Arguments.of(new PersonWithRangeEvacuation(persons.get(29), false), 2),
                    Arguments.of(new PersonWithRangeEvacuation(persons.get(3), true), 1),
                    Arguments.of(new PersonWithRangeEvacuation(persons.get(11), true), 0),
                    Arguments.of(new PersonWithRangeEvacuation(persons.get(5), false), 3)
            );
        }
    }

    @Nested
    class Task14 {
        @ParameterizedTest
        @MethodSource
        void GivenCar_WhenGetLogisticIndex_ThenEqualsExpected(Car car, int expectedIndex) {
            CarWrapper carWrapper = new CarWrapper(car);

            int actualIndex = carWrapper.getIndex();

            assertThat(actualIndex).isEqualTo(expectedIndex);
        }

        @ParameterizedTest
        @MethodSource
        void GivenCars_WhenTask14_ThenProfitEqualsExpected(List<Car> cars, double expectedProfit) {
            double actualProfit = task14(cars);

            assertThat(actualProfit).isEqualTo(expectedProfit, offset(0.001));
        }

        private static Stream<Arguments> GivenCars_WhenTask14_ThenProfitEqualsExpected() {
            List<List<Car>> cars = UtilsTest.getCars();
            return Stream.of(
                    Arguments.of(cars.get(0), 127.95594d),
                    Arguments.of(cars.get(1), 0d),
                    Arguments.of(cars.get(2), 26.53224d)
            );
        }

        private static Stream<Arguments> GivenCar_WhenGetLogisticIndex_ThenEqualsExpected() {
            List<List<Car>> cars = UtilsTest.getCars();
            return Stream.of(
                    Arguments.of(cars.get(0).get(0), 0),
                    Arguments.of(cars.get(0).get(1), 1),
                    Arguments.of(cars.get(0).get(2), 2),
                    Arguments.of(cars.get(0).get(3), 3),
                    Arguments.of(cars.get(0).get(4), 4),
                    Arguments.of(cars.get(0).get(5), 5),
                    Arguments.of(cars.get(0).get(6), 6)
            );
        }


    }


    @Nested
    class Task15 {
        @ParameterizedTest
        @MethodSource("provideStartedFlowersExpectedFlowers")
        void GivenFlowers_WhenUsedComparator_ThenSortedSuccessful(List<Flower> started, List<Flower> expected) {
            Comparator<Flower> comparator = new FlowerComparator();

            started.sort(comparator);

            assertThat(started).isEqualTo(expected);
        }

        @ParameterizedTest
        @MethodSource("provideStartedFlowers")
        void GivenFlowers_WhenFiltered_ThenSumZero(List<Flower> started) {

            double actualSum = task15(started);

            assertThat(actualSum).isZero();
        }

        @Test
        void GivenFlowers_WhenUsedStream_ThenSumEqualsExpected() {
            List<Flower> flowers = UtilsTest.getFlowers().get(1);

            double actualSum = task15(flowers);

            assertThat(actualSum).isEqualTo(102.53675);
        }


        private static Stream<Arguments> provideStartedFlowers() {
            List<List<Flower>> flowers = UtilsTest.getFlowers();
            return Stream.of(
                    Arguments.of(flowers.get(6)),
                    Arguments.of(flowers.get(7)),
                    Arguments.of(flowers.get(8)),
                    Arguments.of(flowers.get(9))
            );
        }

        private static Stream<Arguments> provideStartedFlowersExpectedFlowers() {
            List<List<Flower>> flowers = UtilsTest.getFlowers();
            return Stream.of(
                    Arguments.of(flowers.get(0), flowers.get(1)),
                    Arguments.of(flowers.get(2), flowers.get(3)),
                    Arguments.of(flowers.get(4), flowers.get(5))
            );
        }
    }

    @Nested
    class Task19 {
        @ParameterizedTest
        @MethodSource("providedStudentsAndGroup")
        void GivenNotEmptyStudents_WhenTask19_ThenAllGroupMatchExpected(List<Student> students, String group) throws IOException {
            BufferedReader br = Mockito.mock(BufferedReader.class);
            Mockito.when(br.readLine()).thenReturn(group);

            List<Student> actualStudents = task19(students, br);

            assertThat(actualStudents).isNotEmpty().extracting(Student::getGroup).allMatch(s -> s.equals(group));
        }

        @ParameterizedTest
        @MethodSource("providedStudentsAndGroupAndSize")
        void GivenStudents_WhenTask19_ThenSizeEqualsExpected(List<Student> students, String group, int sizeExpected) throws IOException {
            BufferedReader br = Mockito.mock(BufferedReader.class);
            Mockito.when(br.readLine()).thenReturn(group);

            List<Student> actualStudents = task19(students, br);

            assertThat(actualStudents).hasSize(sizeExpected);
        }

        private static Stream<Arguments> providedStudentsAndGroup() {
            List<List<Student>> students = UtilsTest.getStudents();
            return Stream.of(
                    Arguments.of(students.get(0), "C-2"),
                    Arguments.of(students.get(0), "M-1"),
                    Arguments.of(students.get(0), "P-1")

            );
        }

        private static Stream<Arguments> providedStudentsAndGroupAndSize() {
            List<List<Student>> students = UtilsTest.getStudents();
            return Stream.of(
                    Arguments.of(students.get(1), "C-2", 5),
                    Arguments.of(students.get(1), "M-1", 3),
                    Arguments.of(students.get(1), "P-1", 3),
                    Arguments.of(students.get(1), "C-3", 2),
                    Arguments.of(students.get(1), "y-1", 0)

            );
        }
    }

    @Nested
    class Task20 {

        @Test
        void GivenStudents_WhenUsedGetCollector_ThenAverageScoreByFacultyEqualsExpected() {
            List<Student> students = UtilsTest.getStudents().get(0);
            Map<String, Double> expected = Map.of(
                    "Physics", 8d,
                    "ComputerScience", 7.5d,
                    "Mathematics", 9.5d,
                    "Chemistry", 9d

            );

            Map<String, Double> actual = students.stream()
                    .collect(getCollector(mappingExaminationByStudentID(Util.getExaminations())));

            assertThat(actual).isEqualTo(expected);
        }

        @ParameterizedTest
        @MethodSource("providedStudents")
        void GivenStudents_WhenTask20_ThenFacultyWithMaxAverageScoreEqualsExpected(List<Student> students, String expectedFaculty) {

            String actualFaculty = task20(students);

            assertThat(actualFaculty).isEqualTo(expectedFaculty);

        }

        private static Stream<Arguments> providedStudents() {
            List<List<Student>> students = UtilsTest.getStudents();
            return Stream.of(
                    Arguments.of(students.get(0), "Mathematics"),
                    Arguments.of(students.get(2), "ComputerScience"),
                    Arguments.of(students.get(3), "Mathematics")

            );
        }
    }


    @Nested
    class Task21{
        @Test
        void GivenStudents_WhenUsedTask21_ThenMapEqualsExpected () {
        List<Student> students = UtilsTest.getStudents().get(0);
        Map<String, Long> expected = Map.of(
                "M-1", 2L,
                "P-1", 1L,
                "C-2", 3L
        );

        Map<String, Long> countingStudentsByGroup = task21(students);

        assertThat(countingStudentsByGroup).isEqualTo(expected);
    }
    }


}
