package by.clevertec.util;

import by.clevertec.model.Flower;

import java.util.Comparator;

public class FlowerComparator implements Comparator<Flower> {
    @Override
    public int compare(Flower o1, Flower o2) {
        Comparator<Flower> originReversed = Comparator.comparing(Flower::getOrigin).reversed();
        Comparator<Flower> price = Comparator.comparingInt(Flower::getPrice);
        Comparator<Flower> waterConsumptionReversed = Comparator.comparingDouble(Flower::getWaterConsumptionPerDay).reversed();


        return originReversed.thenComparing(price).thenComparing(waterConsumptionReversed).compare(o1, o2);
    }
}
