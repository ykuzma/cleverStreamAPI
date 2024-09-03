package by.clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalWrapper {
    private static int counter = 1;
    private static int capacityZoo = 7;

    private Animal animal;
    private int numberZoo;


    public static AnimalWrapper createAnimalWrapper(Animal animal){
        if(capacityZoo > 0) {
            capacityZoo--;
        }else {
            counter++;
            capacityZoo = 6;
        }
        return new AnimalWrapper(animal, counter);
    }

}
