package cz.los.KL_Twitter.auth;

import cz.los.KL_Twitter.model.PersistenceEntity;
import cz.los.KL_Twitter.model.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.time.LocalDateTime;

@Value
@RequiredArgsConstructor
public class Session implements PersistenceEntity {

    @Setter
    @NonFinal
    Long id;
    User loggedInUser;
    UserAuthentication userAuthentication;
    LocalDateTime started;
    @Setter
    @NonFinal
    LocalDateTime ended;

    public Session(Session other) {
        this.id = other.id;
        this.loggedInUser = other.loggedInUser;
        this.userAuthentication = other.userAuthentication;
        this.started = other.started;
        this.ended = other.ended;
    }

    public boolean isActive() {
        return ended == null;
    }
}
