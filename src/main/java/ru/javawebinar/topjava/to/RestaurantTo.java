package ru.javawebinar.topjava.to;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class RestaurantTo extends BaseTo {

    private final String description;

    private final int percentageVoters;

    @ConstructorProperties({"description", "percentageVoters"})
    public RestaurantTo(String description, int percentageVoters) {
        this.description = description;
        this.percentageVoters = percentageVoters;
    }


    public String getDescription() {
        return description;
    }

    public int getPercentageVoters() {
        return percentageVoters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantTo restaurantTo = (RestaurantTo) o;
        return percentageVoters == restaurantTo.percentageVoters &&
                Objects.equals(description, restaurantTo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, percentageVoters);
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", percentageVoters=" + percentageVoters +
                '}';
    }
}
