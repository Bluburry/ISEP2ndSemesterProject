package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RespondBookingRequestController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class LogInControllerFX {
    private final RespondBookingRequestController controller = new RespondBookingRequestController();
    private final AuthenticationController ctrl = new AuthenticationController();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLogIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(RespondBookingRequestControllerFX.class.getResource("LogIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRespBookReq00(ActionEvent event) throws IOException {
        stage.close();
        root = FXMLLoader.load(RespondBookingRequestControllerFX.class.getResource("RespondBookingRequest_00.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Label systemRespond;
    @FXML
    private TextField keepUserEmailTextField;
    @FXML
    private PasswordField enterAgentPassTextField;

    @FXML
    private Button cancellButton;

    public void cancelButtonActionEvent(ActionEvent event) {
        Stage stage = (Stage) cancellButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button logInButton;
    @FXML
    private Label logInMessageLabel;
    @FXML
    private BorderPane primaryPanel;


    public void logInButtonActionEvent(ActionEvent event) throws IOException {
        if ((!keepUserEmailTextField.getText().isBlank()) || (!enterAgentPassTextField.getText().isBlank())) {
            validateLogIn();
            stage = (Stage) cancellButton.getScene().getWindow();
            stage.close();
            switchToRespBookReq00(event);
        } else {
            logInMessageLabel.setText("Please enter valid email and Password");
        }
    }

    public void validateLogIn() {
        boolean success = doLogin();
        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                System.out.println("No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRolewithString(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI, role);
                } else {
                    System.out.println("No role selected.");
                }
            }
        }
        this.logout();
    }

    private List<MenuItem> getMenuItemForRoles() {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new AdminUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_OWNER, new OwnerUI()));
//        rolesUI.add(new MenuItem(AuthenticationController.ROLE_AGENT, new AgentUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_AGENT, new AgentUIFX()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_CLIENT, new ClientUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_STORE_NETWORK_MANAGER, new NetworkManagerUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_STORE_MANAGER, new StoreManagerUI()));

        //TODO: Complete with other user roles and related RoleUI
        return rolesUI;
    }

    private boolean doLogin() {

        int maxAttempts = 3;
        boolean success = false;
        do {
            maxAttempts--;
            String id = keepUserEmailTextField.getText();
            String pwd = enterAgentPassTextField.getText();

            success = ctrl.doLogin(id, pwd);
            if (!success) {
                logInMessageLabel.setText("Invalid UserId and/or Password.");
            }

        } while (!success && maxAttempts > 0);
        return success;
    }

    private void logout() {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                item.run();
            }
        }
        if (!found) {
            logInMessageLabel.setText("There is no UI for users with role");
        }
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
        }
    }

    private UserRoleDTO selectsRolewithString(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            logInMessageLabel.setText("Select the role you want to adopt in this session:");
            for (int i = 0; i < roles.size(); i++) {
                logInMessageLabel.setText((i + 1) + ". " + roles.get(i).getDescription());
            }
            logInMessageLabel.setText(" 0 - Cancel ");
            return roles.get(Utils.readIntegerFromConsole("Type your option:") - 1);
        }
    }

}
