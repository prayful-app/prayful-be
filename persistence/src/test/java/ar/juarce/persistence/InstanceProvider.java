package ar.juarce.persistence;

import ar.juarce.models.User;

public class InstanceProvider {

    /* default */ static final String USER_NEW_USERNAME = "new";

    /* default */ static final Long USER_1_ID = 1L;
    /* default */ static final String USER_1_USERNAME = "user";

    private InstanceProvider() {
        throw new AssertionError("This class should not be instantiated");
    }

    /* default */ static User user1() {
        return User.builder()
                .id(USER_1_ID)
                .username(USER_1_USERNAME)
                .build();
    }

}
