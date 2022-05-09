package ru.javawebinar.topjava.to;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class RestaurantTo extends BaseTo {

    private final String description;

    private final int numberOfVotes;

    @ConstructorProperties({"description", "numberOfVotes"})
    public RestaurantTo(String description, int percentageVoters) {
        this.description = description;
        this.numberOfVotes = percentageVoters;
    }


    public String getDescription() {
        return description;
    }

    public int getnumberOfVotes() {
        return numberOfVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantTo restaurantTo = (RestaurantTo) o;
        return numberOfVotes == restaurantTo.numberOfVotes &&
                Objects.equals(description, restaurantTo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, numberOfVotes);
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", percentageVoters=" + numberOfVotes +
                '}';
    }
}
