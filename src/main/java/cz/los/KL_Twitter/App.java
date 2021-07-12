package cz.los.KL_Twitter;

import static cz.los.KL_Twitter.config.Configurator.initApp;

public class App {

    private App() {
    }

    public static void main(String[] args) {
        // parse initial arguments and configure the app
        initApp(args);
        // run app
        run();

    }

    private static void run() {
        System.out.println("Hello world!");
        System.exit(0);
    }

}
