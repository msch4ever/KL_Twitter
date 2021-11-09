package cz.los.KL_Twitter.auth;

import cz.los.KL_Twitter.model.PersistenceEntity;
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
    UserAuthentication userAuthentication;
    LocalDateTime start;
    @Setter
    @NonFinal
    LocalDateTime end;

    public Session(Session other) {
        this.id = other.id;
        this.userAuthentication = other.userAuthentication;
        this.start = other.start;
        this.end = other.end;
    }

    public boolean isActive() {
        return end == null;
    }
}
