package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Kotel;
import org.json.JSONObject;


import java.util.List;


import static controller.Http.getKotely;


public class KotelyListViewController {

    private JSONObject object;
    private List<Kotel> kotels;
    private static int counter = 0;
    private static int pageNumber = 1;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageView;

    @FXML
    private Label lblVotes;

    @FXML
    private Label lblName;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnPrevious;


    public void btnNextPressed(ActionEvent event) throws Exception {
        btnPrevious.setDisable(false);

        if (counter == (kotels.size()-1)){
            btnNext.setDisable(true);
            pageNumber++;
            counter = 0;

            kotels = getKotely(object, pageNumber);

            btnNext.setDisable(false);
            getKotel(counter);
        } else{
            counter++;
            getKotel(counter);
        }
    }

    @FXML
    void btnPreviousPressed(ActionEvent event) throws Exception {

        if (counter == 0) {
            btnPrevious.setDisable(true);
            if (pageNumber != 0) {
                pageNumber--;
                counter = 24;

                kotels = getKotely(object, pageNumber);

                btnPrevious.setDisable(false);
                getKotel(counter);
            }

        } else {
            counter--;
            getKotel(counter);
        }
    }

    public void setJsonObject(JSONObject object) throws Exception {
        this.object = object;

        btnPrevious.setDisable(true);

        kotels = getKotely(object, pageNumber);

        getKotel(counter);

    }

    public void getKotel(int counter) throws Exception {
        lblName.setText(kotels.get(counter).getName());
        lblVotes.setText(String.valueOf(kotels.get(counter).getVotes()));
        Image image = new Image(kotels.get(counter).getURL());
        imageView.setImage(image);
    }
}
