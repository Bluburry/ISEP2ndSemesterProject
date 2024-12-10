package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AgentUI implements Runnable {

    public AgentUI() {

    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create Sale Announcement", new CreateSaleAnnouncementUI()));
        options.add(new MenuItem("List Real Estate Purchase Orders", new AcceptOrDeclineOffersUI()));
        options.add(new MenuItem("List Sale Announcement Requests", new ListPropertyRequestUI()));
        options.add(new MenuItem("List Booking Requests", new ListBookingRequestUI()));
        options.add(new MenuItem("Respond to Booking Request", new RespondBookingRequestUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAgent Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
