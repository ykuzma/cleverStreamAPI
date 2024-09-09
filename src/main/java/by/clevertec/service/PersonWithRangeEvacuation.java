package by.clevertec.service;


import by.clevertec.model.Person;
import by.clevertec.util.TaskUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonWithRangeEvacuation implements EvacuationRank {

    private Person person;
    private boolean inHospital;

    @Override
    public int getRank() {
        int rank = 0;
        if(!isInHospital()) rank += 2;
        if(getAge() < TaskUtil.getPensionAge() && getAge() > 17) rank += 1;
        return rank;
    }

    private int getAge() {
       return Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
    }

}
