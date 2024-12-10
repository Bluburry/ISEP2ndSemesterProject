package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.domain.Serialization;

public class Main {

    public static void main(String[] args) {
        // RespondBookingRequestApplication.main();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        // DealsApplication.main();
        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Serialization.saveAll();
    }
}
