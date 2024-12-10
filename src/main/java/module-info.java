module project.template {
    requires javafx.controls;
    requires javafx.fxml;
    requires AuthLib;
    requires org.apache.commons.lang3;
    // requires jdk.jshell;
    requires java.logging;
    requires commons.math3;
    // requires commons.math3;

    exports pt.ipp.isep.dei.esoft.project.domain to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.application.controller to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.repository to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.gui.ui to javafx.graphics;

    opens pt.ipp.isep.dei.esoft.project.ui.gui.ui to javafx.fxml;
}