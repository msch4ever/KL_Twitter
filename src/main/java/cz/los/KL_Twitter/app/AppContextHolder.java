package cz.los.KL_Twitter.app;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppContextHolder {

    private static AppContext appContext;

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

}
