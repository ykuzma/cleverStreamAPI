package by.clevertec.util;

import by.clevertec.model.Animal;

import java.util.function.Predicate;

public class TaskUtil {

   public static Predicate<Animal> isMale = animal -> animal.getGender().equals("Male");
   public static Predicate<Animal> isFemale = animal -> animal.getGender().equals("Female");


}
