package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.to.RestaurantTo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class VoteUtil {

    private VoteUtil() {
    }

    public static List<RestaurantTo> getTos(Collection<Vote> votes) {
        return filterByPredicate(votes, vote -> true);
    }

    public static List<RestaurantTo> filterByPredicate(Collection<Vote> votes, Predicate<Vote> filter) {
        Map<Restaurant, Long> countByRestaurant = votes.stream()
                .collect(
                        Collectors.groupingBy(Vote::getRestaurant, Collectors.counting()));

        return votes.stream()
                .filter(filter)
                .map(vote -> createTo(vote, Math.toIntExact(countByRestaurant.get(vote.getRestaurant()))))
                .distinct()
                .toList();
    }

    ///LAZY off(
    public static RestaurantTo createTo(Vote vote, int countVote) {
        return new RestaurantTo(vote.getRestaurant().getDescription(), countVote);
    }
}
