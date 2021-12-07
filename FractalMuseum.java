import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *This class is FractalMuseum
 *@author Dongheon Han
 *@version 1.0
 * I worked on the homework assignment alone, using only course materials.
 */
public class FractalMuseum extends Application {
    private static int numOfSquare = 0;
    private int p1score = 0;
    private int p2score = 0;
    private static boolean createFrac = false;
    private static boolean submitGuessesBtnKey = false;
    private String theWinnerIs = null;
    private static boolean errorOccured = false;

    /**
     * Getter for numOfSquare
     * @return int numOfSquare
     */
    public int getNumOfSquare() {
        return numOfSquare;
    }

    /**
     * Getter for p1score
     * @return int p1score
     */
    public int getP1score() {
        return p1score;
    }

    /**
     * Getter for p2score
     * @return int p2score
     */
    public int getP2score() {
        return p2score;
    }

    /**
     * getter for TheWinnerIs
     * @return String TheWinnerIs
     */
    public String getTheWinnerIs() {
        return theWinnerIs;
    }

    /**
     * this method drives the class
     * @param args main method
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * this method starts the program
     * @param mainStage Stage type Parameter
     */
    public void start(Stage mainStage) {
        Text titleOftheGame = new Text(50, 100, "Fractal Museum Game by Don Han");
        titleOftheGame.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 30));
        titleOftheGame.setFill(Color.YELLOW);
        titleOftheGame.setStroke(Color.BLACK);
        titleOftheGame.setStrokeWidth(2);
        HBox titleOftheGameHbox = new HBox(titleOftheGame);
        titleOftheGameHbox.setAlignment(Pos.CENTER);
        Text titleOftheGame2 = new Text(50, 100, "***CS1331 HW8                                 "
                + "                                                       ");
        titleOftheGame2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        titleOftheGame2.setFill(Color.YELLOW);
        titleOftheGame2.setStroke(Color.BLACK);
        HBox titleOftheGameHbox2 = new HBox(titleOftheGame2);
        titleOftheGameHbox2.setAlignment(Pos.CENTER);
        mainStage.setTitle("Fractal Museum");
        Button newGameBtn = new Button();
        Button submitGuessesBtn = new Button();
        newGameBtn.setText("New Game!");
        submitGuessesBtn.setText("Submit Guesses!");
        newGameBtn.setStyle("-fx-background-color: yellow");
        submitGuessesBtn.setStyle("-fx-background-color: yellow");
        Label nextLine = new Label("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        Label nextLine2 = new Label("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        Label smallerNextLine = new Label("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        TextField player1Input = new TextField();
        TextField player2Input = new TextField();
        VBox mainSceneRoot = new VBox();
        HBox newGameHbox = new HBox(newGameBtn);
        HBox p1p2SubGuess = new HBox();
        HBox p1p2Score = new HBox();
        HBox numFrac = new HBox();
        BorderPane rootFRAC = new BorderPane();
        BorderPane.setAlignment(rootFRAC, Pos.CENTER);
        VBox mainSceneRootNewGameVBox = new VBox();
        /**
         * EventHandler Anonymous Class
         */
        EventHandler<ActionEvent> handler1 = new EventHandler<>() {
            @Override
            public void handle(ActionEvent event1) {
                createFrac = true;
                start(mainStage);
            }
        };
        newGameBtn.setOnAction(handler1);
        if (createFrac) {
            numOfSquare = FractalFactory.drawFractal(rootFRAC);
            createFrac = false;
        }
        HBox numFracNewGame = new HBox(rootFRAC);
        numFracNewGame.setAlignment(Pos.CENTER);

        /**
         * Lamda expression, submitGuessesBtn.setOnAction
         */
        submitGuessesBtn.setOnAction(event -> {
                submitGuessesBtnKey = true;
                createFrac = false;
                String player1Guess = player1Input.getCharacters().toString();
                String player2Guess = player2Input.getCharacters().toString();
                try {
                    int p1Guess = Integer.parseInt(player1Guess);
                    int p2Guess = Integer.parseInt(player2Guess);
                    if (p1Guess < 0 || p2Guess < 0) {
                        throw new NegativeInputException();
                    }
                    if (numOfSquare == 0) {
                        throw new NumberOfSquareIsZeroException();
                    }
                    if (numOfSquare > 0 && p1Guess > 0 && p2Guess > 0) {
                        if (Math.abs(p1Guess - numOfSquare) > Math.abs(p2Guess - numOfSquare)) {
                            p2score++;
                            theWinnerIs = "the Winner is \"Player 2\"";
                        } else if (Math.abs(p1Guess - numOfSquare) < Math.abs(p2Guess - numOfSquare)) {
                            p1score++;
                            theWinnerIs = "the Winner is \"Player 1\"";
                        } else {
                            theWinnerIs = "TIE";
                        }
                    }
                    System.out.println(numOfSquare);
                    System.out.println(Math.abs(p1Guess - numOfSquare));
                    System.out.println(Math.abs(p2Guess - numOfSquare));
                    System.out.println(Math.abs(p1Guess - numOfSquare) > Math.abs(p2Guess - numOfSquare));
                    start(mainStage);
                } catch (NegativeInputException e) {
                    theWinnerIs = "Press \"New Game!\" button again to start the next round";
                    errorOccured = true;
                    submitGuessesBtn.setDisable(true);
                    Alert nIE = new Alert(Alert.AlertType.ERROR);
                    nIE.setTitle("Error");
                    nIE.setHeaderText("Invalid Input: Negative Number is not accepted");
                    nIE.setContentText("Press New Game and Put the positive number instead");
                    nIE.showAndWait();
                } catch (NumberOfSquareIsZeroException e) {
                    theWinnerIs = "Press \"New Game!\" button again to start the next round";
                    errorOccured = true;
                    submitGuessesBtn.setDisable(true);
                    Alert nSZE = new Alert(Alert.AlertType.ERROR);
                    nSZE.setTitle("Error");
                    nSZE.setHeaderText("the number of square is Zero");
                    nSZE.setContentText("Press New Game and press Submit Guesses Button");
                    nSZE.showAndWait();
                } catch (NumberFormatException e) {
                    theWinnerIs = "Press \"New Game!\" button again to start the next round";
                    errorOccured = true;
                    submitGuessesBtn.setDisable(true);
                    Alert nFE = new Alert(Alert.AlertType.ERROR);
                    nFE.setTitle("Error");
                    nFE.setHeaderText("Invalid Input");
                    nFE.setContentText("Press New Game and Put the valid input instead");
                    nFE.showAndWait();
                }
            });
        Label player1 = new Label("Player1  ");
        Label player2 = new Label("  Player2  ");
        Label player1Label = new Label(String.format("Player1 Score: %d", p1score));
        Label spaceBetween = new Label("                                           ");
        Label player2Label = new Label(String.format("Player2 Score: %d", p2score));
        Label numOfFrac = new Label(String.format("Number of Fractals: %d", numOfSquare));
        HBox numOfFracHBox = new HBox(numOfFrac);
        numOfFracHBox.setAlignment(Pos.CENTER);
        p1p2SubGuess.setAlignment(Pos.CENTER);
        newGameHbox.setAlignment(Pos.CENTER);
        p1p2SubGuess.setAlignment(Pos.CENTER);
        p1p2Score.setAlignment(Pos.CENTER);
        p1p2SubGuess.getChildren().addAll(player1, player1Input, player2, player2Input, submitGuessesBtn);
        p1p2Score.getChildren().addAll(player1Label, spaceBetween, player2Label);
        if (submitGuessesBtnKey && !errorOccured) {
            System.out.println("submitGuessesBtnKey == true");
            submitGuessesBtn.setDisable(true);
            submitGuessesBtnKey = false;
            createFrac = false;
            Label resultLabelWinner = new Label(theWinnerIs);
            resultLabelWinner.setAlignment(Pos.CENTER);
            HBox resultIS = new HBox(resultLabelWinner);
            resultIS.setAlignment(Pos.CENTER);
            mainSceneRoot.getChildren().addAll(titleOftheGameHbox, titleOftheGameHbox2, newGameHbox, nextLine
                    , numOfFracHBox, resultIS, smallerNextLine, p1p2SubGuess, p1p2Score, numFrac);
        } else if (errorOccured) {
            submitGuessesBtnKey = false;
            createFrac = false;
            Label resultLabelWinner = new Label(theWinnerIs);
            resultLabelWinner.setAlignment(Pos.CENTER);
            HBox resultIS = new HBox(resultLabelWinner);
            resultIS.setAlignment(Pos.CENTER);
            mainSceneRoot.getChildren().addAll(titleOftheGameHbox, titleOftheGameHbox2, newGameHbox, nextLine
                    , resultIS);
            errorOccured = false;
        } else {
            mainSceneRoot.getChildren().addAll(titleOftheGameHbox, titleOftheGameHbox2, newGameHbox, nextLine
                    , numFracNewGame, nextLine2, p1p2SubGuess, p1p2Score, numFrac);
        }
        Scene scene = new Scene(mainSceneRoot, 700, 700);
        mainStage.setScene(scene);
        scene.setFill(Color.YELLOWGREEN);
        mainStage.show();
    }
}