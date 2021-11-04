package cz.los.KL_Twitter.auth;

import lombok.Value;
import lombok.experimental.NonFinal;

import java.time.LocalDateTime;

@Value
public class Session {

    @NonFinal
    Long sessionId;
    UserLogin userLogin;
    LocalDateTime start;
    LocalDateTime end;

}
