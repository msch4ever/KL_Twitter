package cz.los.KL_Twitter.app;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContextHolder {

    private static AppContext appContext;
    private static SecurityContext securityContext;

    public static AppContext getAppContext() {
        if (appContext == null) {
            throw new AppContextException("Application context not yet initialized!");
        }
        return appContext;
    }

    public static void initAppContext(AppContext newAppContext) {
        if (appContext != null) {
            throw new AppContextException("Application context already initialized!");
        }
        appContext = newAppContext;
    }

    public static SecurityContext getSecurityContext() {
        if (securityContext == null) {
            throw new AppContextException("Application context not yet initialized!");
        }
        return securityContext;
    }

    public static void initSecurityContext(SecurityContext newSecurityContext) {
        if (securityContext != null) {
            throw new AppContextException("Application context already initialized!");
        }
        securityContext = newSecurityContext;
    }

}
