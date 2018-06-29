package controller;

import controller.Http;
import controller.KotelyListViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;


public class LoginController{


    @FXML
    private TextField emailText;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField passText;

    @FXML
    private AnchorPane anchorPaneBase;


    public void btnLoginPressed(javafx.event.ActionEvent event) throws Exception {

        String email = emailText.getText();
        String pass = passText.getText();

        JSONObject response = null;

        try {
            response = Http.connect(email, pass);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Koteły error");
            alert.setHeaderText("Error");
            alert.setContentText("Wprowadzono niepoprawne dane");
            alert.showAndWait();
        }

        if (response != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/kotely_listview.fxml"));


            loader.load();

            KotelyListViewController kotely = loader.getController();
            kotely.setJsonObject(response);
            Parent p = loader.getRoot();
            anchorPaneBase.getChildren().setAll(p);



        }



    }


}
