package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.PersonWithRangeEvacuation;
import by.clevertec.util.EvacuationRank;
import by.clevertec.util.FlowerComparator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static by.clevertec.Main.task13;
import static by.clevertec.Main.task15;
import static by.clevertec.Main.task2;
import static by.clevertec.Main.task4;
import static org.assertj.core.api.Assertions.assertThat;


class MainTest {

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

    @ParameterizedTest
    @MethodSource("provideStartListAndExpectedCount")
    void testTask4_countEqualsExpected(List<Animal> animals, long expected) {

        Long count = task4(animals);

        assertThat(count).isEqualTo(expected);
    }

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


    private static Stream<Arguments> provideStartListAndExpectedCount() {
        List<List<Animal>> animals = UtilsTest.getAnimals();
        return Stream.of(
                Arguments.of(animals.get(0), 1),
                Arguments.of(animals.get(1), 1),
                Arguments.of(animals.get(2), 4),
                Arguments.of(animals.get(3), 0)
        );
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


}
