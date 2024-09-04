package by.clevertec.util;

import by.clevertec.model.Animal;
import by.clevertec.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TaskUtil {

   public static Supplier<String> orElse = () -> "Empty Stream";

   public static Predicate<Animal> isMale = animal -> animal.getGender().equals("Male");
   public static Predicate<Animal> isFemale = animal -> animal.getGender().equals("Female");

   public static Predicate<Animal> isOrigin(String country){
      return animal -> animal.getOrigin().equals(country);
   }

   public static Predicate<Person> isAgeOlder(int age) {
      return person ->  Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= age;
   }
   public static Predicate<Person> isAgeYounger(int age) {
      return person ->  Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <= age;
   }

}
