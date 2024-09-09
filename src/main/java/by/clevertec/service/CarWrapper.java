package by.clevertec.service;

import by.clevertec.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarWrapper implements LogisticIndex {
    private Car car;

    @Override
    public int getIndex() {
        int index;
        if(car.getColor().equals("White") || car.getCarMake().equals("Jaguar")){
            index = 0;
        } else if (car.getMass() < 1500 || isEqualsMark("BMW", "Lexus", "Chrysler", "Toyota") ) {
            index = 1;
        } else if (car.getColor().equals("Black") && car.getMass() > 4000 || isEqualsMark("GMC", "Dodge")) {
            index = 2;
        } else if (car.getReleaseYear() < 1982 || isEqualsModel("Civic", "Cherokee")) {
            index = 3;
        }else if(car.getPrice() > 40000 || notEqualsColor("Yellow", "Red", "Green", "Blue")) {
            index = 4;
        } else if (car.getVin().contains("59")) {
            index = 5;
        }else {
            index = 6;
        }

        return index;
    }

    private boolean isEqualsMark(String ...str){
       return Arrays.stream(str).anyMatch(mark -> mark.equals(car.getCarMake()));
    }
    private boolean isEqualsModel(String ...str){
        return Arrays.stream(str).anyMatch(m -> m.equals(car.getCarModel()));
    }
    private boolean notEqualsColor(String ...str){
        return Arrays.stream(str).noneMatch(c -> c.equals(car.getColor()));
    }
}


