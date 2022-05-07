package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant","user");

    public static final int VOTE_ID = START_SEQ + 21;

    public static LocalDateTime votingTimeIsOver = LocalDateTime.of(LocalDateTime.now().getYear(),
            LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0);
    public static LocalDateTime votingIsUnderway = LocalDateTime.of(LocalDateTime.now().getYear(),
            LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0);

    public static final Vote vote1 = new Vote(VOTE_ID, votingIsUnderway);

    public static Vote getNewVote() {
        return new Vote(null, votingIsUnderway);
    }

    public static Vote getUpdatedVote() {
        return new Vote(VOTE_ID, votingIsUnderway);
    }

    public static Vote getNewVoteTimesUp() {
        return new Vote(null, votingTimeIsOver);
    }

    public static Vote getUpdatedVoteTimesUp() {
        return new Vote(VOTE_ID, votingTimeIsOver);
    }
}
