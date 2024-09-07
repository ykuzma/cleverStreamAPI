package by.clevertec.util;

import by.clevertec.model.Flower;

import java.util.Comparator;

public class FlowerComparator implements Comparator<Flower> {
    @Override
    public int compare(Flower o1, Flower o2) {
        Comparator<Flower> reversOrigin = (r1,r2) -> r2.getOrigin().compareTo(r1.getOrigin());
        Comparator<Flower> price = Comparator.comparingInt(Flower::getPrice);
        Comparator<Flower> waterConsumption = Comparator.comparingDouble(Flower::getWaterConsumptionPerDay);


        return reversOrigin.thenComparing(price).thenComparing(waterConsumption).compare(o1, o2);
    }
}
