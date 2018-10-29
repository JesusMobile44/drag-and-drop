import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;


public class Main extends Application {

    Image image0 = new Image("mario0.jpg");
    Image image1 = new Image("mario1.jpg");
    Image image2 = new Image("mario2.jpg");
    Image image3 = new Image("mario3.jpg");
    Image image4 = new Image("mario4.jpg");
    Image image5 = new Image("mario5.jpg");
    Image image6 = new Image("mario6.jpg");
    Image image7 = new Image("mario7.jpg");
    Image image8 = new Image("mario8.jpg");
    ArrayList<Image> arrayImage = new ArrayList<>();
    ArrayList<Image> arrayImageConfirm = new ArrayList<>();
    ArrayList<ImageView> arrayImageView = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage){
        primaryStage.setTitle("Laboratoire 8: Casse-tête pour les 5-20 ans");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.setWidth(930);
        primaryStage.setHeight(930);

        arrayImage.add(image0);
        arrayImage.add(image1);
        arrayImage.add(image2);
        arrayImage.add(image3);
        arrayImage.add(image4);
        arrayImage.add(image5);
        arrayImage.add(image6);
        arrayImage.add(image7);
        arrayImage.add(image8);
        arrayImageConfirm.add(image0);
        arrayImageConfirm.add(image1);
        arrayImageConfirm.add(image2);
        arrayImageConfirm.add(image3);
        arrayImageConfirm.add(image4);
        arrayImageConfirm.add(image5);
        arrayImageConfirm.add(image6);
        arrayImageConfirm.add(image7);
        arrayImageConfirm.add(image8);

        ImageView imageView0 = new ImageView(arrayImage.get(0));
        imageView0.setFitHeight(300);
        imageView0.setFitWidth(300);
        ImageView imageView1 = new ImageView(arrayImage.get(1));
        imageView1.setFitWidth(300);
        imageView1.setFitHeight(300);
        ImageView imageView2 = new ImageView(arrayImage.get(2));
        imageView2.setFitHeight(300);
        imageView2.setFitWidth(300);
        ImageView imageView3 = new ImageView(arrayImage.get(3));
        imageView3.setFitHeight(300);
        imageView3.setFitWidth(300);
        ImageView imageView4 = new ImageView(arrayImage.get(4));
        imageView4.setFitHeight(300);
        imageView4.setFitWidth(300);
        ImageView imageView5 = new ImageView(arrayImage.get(5));
        imageView5.setFitHeight(300);
        imageView5.setFitWidth(300);
        ImageView imageView6 = new ImageView(arrayImage.get(6));
        imageView6.setFitHeight(300);
        imageView6.setFitWidth(300);
        ImageView imageView7 = new ImageView(arrayImage.get(7));
        imageView7.setFitHeight(300);
        imageView7.setFitWidth(300);
        ImageView imageView8 = new ImageView(arrayImage.get(8));
        imageView8.setFitHeight(300);
        imageView8.setFitWidth(300);

        arrayImageView.add(imageView0);
        arrayImageView.add(imageView1);
        arrayImageView.add(imageView2);
        arrayImageView.add(imageView3);
        arrayImageView.add(imageView4);
        arrayImageView.add(imageView5);
        arrayImageView.add(imageView6);
        arrayImageView.add(imageView7);
        arrayImageView.add(imageView8);

        melanger(arrayImageView);


        for (int i=0;i<arrayImageView.size();i++){
            dragDrop(arrayImageView.get(i));
        }

        HBox hBoxH = new HBox(imageView0,imageView1,imageView2);
        hBoxH.setSpacing(5);

        HBox hBoxM = new HBox(imageView3,imageView4,imageView5);
        hBoxM.setSpacing(5);

        HBox hBoxB = new HBox(imageView6,imageView7,imageView8);
        hBoxB.setSpacing(5);

        VBox vBox = new VBox(hBoxH,hBoxM,hBoxB);
        vBox.setSpacing(5);

        BorderPane borderPane = new BorderPane(vBox);

        Scene scene = new Scene(borderPane);

        scene.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.M){
                melanger(arrayImageView);

            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void melanger(ArrayList<ImageView> imageViews){
        Collections.shuffle(arrayImage);
        for (int i=0;i<arrayImage.size();i++){
            imageViews.get(i).setImage(arrayImage.get(i));
        }
    }
    public void dragDrop(ImageView source){
        source.setOnDragDetected(event -> {
            Image image = source.getImage();
            Dragboard dragboard = source.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent contenu = new ClipboardContent();
            contenu.putImage(image);
            dragboard.setContent(contenu);
        });
        source.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.MOVE);
        });
        source.setOnDragDone(event -> {
            verifier();
        });
        source.setOnDragDropped(event -> {
            ImageView sourceImage = (ImageView)event.getGestureSource();
            ImageView targetImage = (ImageView)event.getGestureTarget();
            Image temp = sourceImage.getImage();
            sourceImage.setImage(targetImage.getImage());
            targetImage.setImage(temp);
            event.setDropCompleted(true);
        });
    }
    public void verifier(){
        boolean ok = true;
        for (int i=0;i<arrayImageConfirm.size();i++){
            if (ok){
                ok=arrayImageView.get(i).getImage().equals(arrayImageConfirm.get(i));
                System.out.println("false "+i);
            }
        }
        if (ok){
            Label bravoLabel = new Label("FÉLICITATION BB");

            HBox hBox = new HBox(bravoLabel);

            Dialog dialog = new Dialog();
            dialog.getDialogPane().setContent(hBox);

            dialog.getDialogPane().getButtonTypes().add(
                    new ButtonType("Rejouer")
            );
            dialog.showAndWait();
            melanger(arrayImageView);

        }
    }

}
