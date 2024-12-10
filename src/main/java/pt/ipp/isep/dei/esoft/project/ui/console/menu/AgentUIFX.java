package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import javafx.event.ActionEvent;
import javafx.event.Event;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.ui.console.AcceptOrDeclineOffersUI;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateSaleAnnouncementUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ListPropertyRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.LogInControllerFX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgentUIFX implements Runnable {

    public AgentUIFX() {

    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create Sale Announcement", new CreateSaleAnnouncementUI()));
        options.add(new MenuItem("List Real Estate Purchase Orders", new AcceptOrDeclineOffersUI()));
        options.add(new MenuItem("List Sale Announcement Requests", new ListPropertyRequestUI()));
        options.add(new MenuItem("Option 4 ", new ShowTextUI("You have chosen Option D.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAgent Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
