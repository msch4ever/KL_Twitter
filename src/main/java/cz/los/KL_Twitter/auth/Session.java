package cz.los.KL_Twitter.auth;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.time.LocalDateTime;

@Value
@RequiredArgsConstructor
public class Session {

    Long sessionId;
    UserAuthentication userAuthentication;
    LocalDateTime start;
    @Setter
    @NonFinal
    LocalDateTime end;
}
