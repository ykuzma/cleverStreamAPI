package by.clevertec;

import by.clevertec.model.Animal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static by.clevertec.Main.task2;
import static org.assertj.core.api.Assertions.assertThat;


 class MainTest {

    @ParameterizedTest
    @MethodSource("provideTestTask2Arguments")
    void testTask2_resultListEqualsExpected(List<Animal> animals, List<String> expected) {

       List<String> result = task2(animals);

      assertThat(result).isEqualTo(expected);

    }

     @Test
     void testTask2_resultListSizeEqualsExpected() {
         List<Animal> animals = UtilsTest.getAnimals().get(2);

         Integer result = task2(animals).size();

         assertThat(result).isEqualTo(2);
     }

     @Test
     void testTask2_allElementsIsUpperCase() {
         List<Animal> animals = UtilsTest.getAnimals().get(2);

         List<String> result = task2(animals);

         assertThat(result.stream()
                 .allMatch(s -> s.equals(s.toUpperCase()))).isTrue();
     }

     private static Stream<Arguments> provideTestTask2Arguments() {
        List<List<Animal>> animals = UtilsTest.getAnimals();
         return Stream.of(
                 Arguments.of(animals.get(0), List.of("JF")),
                 Arguments.of(animals.get(1), List.of()),
                 Arguments.of(animals.get(2), List.of("JF", "JF"))
         );
     }

}
