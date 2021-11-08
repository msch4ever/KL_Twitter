package cz.los.KL_Twitter.auth;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.time.LocalDateTime;

@Value
@RequiredArgsConstructor
public class Session {

    @Setter
    @NonFinal
    Long sessionId;
    UserAuthentication userAuthentication;
    LocalDateTime start;
    @Setter
    @NonFinal
    LocalDateTime end;

    public Session(Session other) {
        this.sessionId = other.sessionId;
        this.userAuthentication = other.userAuthentication;
        this.start = other.start;
        this.end = other.end;
    }
}
