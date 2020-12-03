package ModelViewController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class View extends Application {

    StringProperty valueProperty;
    int secondCard;
    int tmpp = 0;
    int sirr = 0;
    int sizoe = 8;
    int heysizoe;
    int cards = 0;
    int players = 0;
    int turn = 0;
    boolean whatgame;
    private int flips = 0;
    private TextArea textarea;
    ImageView firstLocation = new ImageView();
    private Stage window;
    private MemoryLogic f;
    private GridPane game;
    int firstX, firstY, secondX, SecondY, firstCard, firstImage, secondImage;

    memoryIO r = new memoryIO();
    private int[] scoresP1 = new int[10];
    private int[] scoresP2 = new int[10];
    private int nrOfScoresP1 = 0;
    private int nrOfScoresP2 = 0;

    public View() {
        game = new GridPane();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Scene scene = startMenu();

        primaryStage.setTitle("\t Memory");

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public Scene startMenu() throws Exception {
        try {
            scoresP1 = r.deSerializeFromFile(scoresP1, "hej.txt");
        } catch (IOException e) {
            System.out.println("lol P1");
        }
        nrOfScoresP1 = scoresP1[0];
        System.out.println("nrOfScores P1: " + nrOfScoresP1);

        try {
            scoresP2 = r.deSerializeFromFile(scoresP2, "hejP2.txt");
        } catch (IOException e) {
            System.out.println("lol P2");
        }
        nrOfScoresP2 = scoresP2[0];
        System.out.println("nrOfScores P2: " + nrOfScoresP2);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(3);
        grid.setHgap(3);

        Scene scene = new Scene(grid, 500, 500);

        Label memory = new Label("Memory");
        GridPane.setConstraints(memory, 70, 30);

        Label twoPlayer = new Label("Play");
        twoPlayer.setOnMouseClicked(e -> window.setScene(Difficulty(scene)));
        GridPane.setConstraints(twoPlayer, 70, 50);

        Label instructions = new Label("Instructions");
        instructions.setOnMouseClicked(e -> window.setScene(Instructions()));
        GridPane.setConstraints(instructions, 70, 52);

        Label statistacs = new Label("Highscore");
        statistacs.setOnMouseClicked(e -> window.setScene(Statistacs()));
        GridPane.setConstraints(statistacs, 70, 54);

        Label exit = new Label("Exit");
        exit.setOnMouseClicked(new ExitPressed());
        GridPane.setConstraints(exit, 70, 56);

        grid.getChildren().addAll(twoPlayer, memory, exit, instructions, statistacs);
        return scene;
    }

    public Scene Difficulty(Scene scene) {
        GridPane difficulty = new GridPane();
        difficulty.setPadding(new Insets(10, 10, 10, 10));
        difficulty.setVgap(3);
        difficulty.setHgap(3);

        Scene scene2 = new Scene(difficulty, 500, 500);

        Label easy = new Label("Fruits");
        GridPane.setConstraints(easy, 70, 52);
        easy.setOnMouseClicked(e -> window.setScene(game(8, scene, 1)));

        Label medium = new Label("League Of Legends");
        GridPane.setConstraints(medium, 70, 54);
        medium.setOnMouseClicked(e -> window.setScene(game(8, scene, 0)));

        Label goBack = new Label("Go back");
        GridPane.setConstraints(goBack, 70, 58);
        goBack.setOnMouseClicked(e -> window.setScene(scene));

        difficulty.getChildren().addAll(easy, medium, goBack);

        return scene2;
    }

    public Scene Instructions() {
        GridPane difficulty = new GridPane();
        difficulty.setPadding(new Insets(10, 10, 10, 10));
        difficulty.setVgap(3);
        difficulty.setHgap(3);

        Label header = new Label("Instructions:");
        GridPane.setConstraints(header, 30, 36);

        Label instr = new Label(" The purpose of this game is to find matching pairs by flipping\n"
                + " cards. If a pair has been found it gets deleted, if not they'll\n "
                + "flip back to hide themselves");
        GridPane.setConstraints(instr, 30, 37);

        difficulty.getChildren().addAll(instr, header);

        Scene scene3 = new Scene(difficulty, 500, 500);
        return scene3;
    }

    public Scene Statistacs() {
        GridPane statistacs = new GridPane();
        statistacs.setPadding(new Insets(10, 10, 10, 10));
        statistacs.setVgap(3);
        statistacs.setHgap(3);

        Label title = new Label("\t\t\tPlayer One Points\t\t\t Player Two Points\n_________________________________________________________________________");
        GridPane.setConstraints(title, 10, 38);

        Label Players = new Label("\nGame#1 \t\t " + scoresP1[1] + "\t\t\t\t\t     \t " + scoresP2[1]
                + "\nGame#2\t    \t" + scoresP1[2] + "\t\t\t\t\t     \t " + scoresP2[2]
                + "\nGame#3\t   \t" + scoresP1[3] + "\t\t\t\t\t     \t " + scoresP2[3]
                + "\nGame#4\t    \t" + scoresP1[4] + "\t\t\t\t\t     \t " + scoresP2[4]
                + "\nGame#5\t  \t" + scoresP1[5] + "\t\t\t\t\t     \t " + scoresP2[5]
                + "\nGame#6\t     \t" + scoresP1[6] + "\t\t\t\t\t     \t " + scoresP2[6]
                + "\nGame#7\t   \t" + scoresP1[7] + "\t\t\t\t\t     \t " + scoresP2[7]
                + "\nGame#8\t     \t" + scoresP1[8] + "\t\t\t\t\t     \t " + scoresP2[8]
                + "\nGame#9\t     \t" + scoresP1[9] + "\t\t\t\t\t     \t " + scoresP2[9]);
        GridPane.setConstraints(Players, 10, 40);

        statistacs.getChildren().addAll(title, Players);

        Scene scene4 = new Scene(statistacs, 500, 500);
        return scene4;
    }

    public Scene game(int size, Scene scene, int theme) {

        int tmp;
        tmp = size;
        f = new MemoryLogic(size, theme);
        GridPane w = new GridPane();
        MemoryLogic s = new MemoryLogic(size, theme);
        whatgame = f.getGame();
        firstCard = 0;
        f = s;
        game = w;

        w.setVgap(1);
        w.setHgap(1);

        List<Node> children = w.getChildren();

        int row = 50;
        int column = 71;

        Menu mo = new Menu("Options");

        MenuItem mo1 = new MenuItem("Instructions");
        MenuItem mo2 = new MenuItem("Reset");
        MenuItem mo3 = new MenuItem("Go to menu");

        mo.getItems().add(mo1);
        mo.getItems().add(mo2);
        mo.getItems().add(mo3);

        EventHandler<ActionEvent> evento = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Label secondLabel = new Label(" The purpose of this game is to find matching pairs by flipping\n"
                        + " cards. If a pair has been found it gets deleted, if not they'll\n "
                        + "flip back to hide themselves");
                GridPane secondLayout = new GridPane();
                secondLayout.getChildren().add(secondLabel);

                Scene secondScene = new Scene(secondLayout, 350, 70);

                Stage newWindow = new Stage();
                newWindow.setTitle("Instructions");
                newWindow.setScene(secondScene);

                newWindow.show();

            }
        };

        mo1.setOnAction(evento);

        MenuBar mos = new MenuBar();
        mos.getMenus().add(mo);

        for (int i = 0; i < sizoe * 2; i++) {
            Image m = new Image("BackLayer.png", 50, 50, false, false);
            ImageView firstLocation = new ImageView();
            firstLocation.setImage(m);
            firstLocation.setOnMouseClicked(new LocationPressed(column, row, i));
            GridPane.setConstraints(firstLocation, column, row);
            children.add(firstLocation);
            column += 8;
            if (column == 103) {
                column = 71;
                row++;
            }
        }

        w.getChildren().add(mos);

        Label PointsOne = new Label("P1:" + f.getPlayer1point());
        GridPane.setConstraints(PointsOne, 0, (row + row / 2));

        Label PointsTwo = new Label("P2:" + f.getPlayer2point());
        GridPane.setConstraints(PointsTwo, 198, (row + row / 2));

//        
        Label playerTurn = new Label("Play");
        playerTurn.setWrapText(true);
        GridPane.setConstraints(playerTurn, 0, (row + row / 2 + 10));
        playerTurn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

        w.getChildren().addAll(PointsOne, PointsTwo, playerTurn);

        tmpp = (size * 2) + 1;

        Scene scene5 = new Scene(game, 500, 500);

        EventHandler<ActionEvent> eventzz = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                try {
                    window.setScene(startMenu());
                } catch (Exception ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };

        mo3.setOnAction(eventzz);

        EventHandler<ActionEvent> eventi = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                sirr++;

                scene5.setRoot(w);

                window.setScene(game(size, scene5, theme));

            }
        };
        mo2.setOnAction(eventi);

        return scene5;
    }

    class optionsPressed implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            throw new UnsupportedOperationException("t supported yet.");
        }

    }

    class LocationPressed implements EventHandler<MouseEvent> {

        int x, y, image;
        boolean flag = false;

        public LocationPressed(int x, int y, int image) {
            this.x = x;
            this.y = y;
            this.image = image;

        }

        @Override
        public void handle(MouseEvent event) {

            firstCard++;
            int row = 54;
            if (firstCard == 1) {
                Image back;
                if (whatgame) {
                    back = new Image(f.getBoard().get(this.image).getImage(), 50, 50, false, false);
                } else {
                    back = new Image(f.getChampBoard().get(this.image).getImage(), 50, 50, false, false);
                }

                ImageView Location = new ImageView();
                Location.setImage(back);
                Location.setOnMouseClicked(new LocationPressed(this.x, this.y, this.image));
                GridPane.setConstraints(Location, this.x, this.y);
                game.getChildren().remove(this.image);
                game.getChildren().add(this.image, Location);
                System.out.println("\nThis is Card:");
                if (whatgame) {
                    System.out.println(" " + f.getBoard().get(this.image).getFruit());
                } else {
                    System.out.println(" " + f.getChampBoard().get(this.image).getLolc());
                }

                firstImage = this.image;
                firstX = this.x;
                firstY = this.y;
            }

            if (firstCard == 2) {

                if (firstImage != this.image) {
                    System.out.println("\nThis is Card:");
                    if (whatgame) {
                        System.out.println(" " + f.getBoard().get(this.image).getFruit());
                    } else {
                        System.out.println(" " + f.getChampBoard().get(this.image).getLolc());
                    }

                    secondImage = this.image;
                    secondX = this.x;
                    SecondY = this.y;

                    ImageView Locationi = new ImageView();
                    Image backo;
                    if (whatgame) {
                        backo = new Image(f.getBoard().get(image).getImage(), 50, 50, false, false);
                    } else {
                        backo = new Image(f.getChampBoard().get(this.image).getImage(), 50, 50, false, false);
                    }
                    Locationi.setImage(backo);
                    Locationi.setOnMouseClicked(new LocationPressed(this.x, this.y, this.image));
                    GridPane.setConstraints(Locationi, this.x, this.y);
                    game.getChildren().remove(this.image);
                    game.getChildren().add(this.image, Locationi);

                    flag = f.flipAndCompare(firstImage, this.image);
                    turn++;

                } else {
                    System.out.println("Cant click same len");
                    firstCard--;

                }

            }
            if (firstCard == 3) {
                ImageView reset = new ImageView();
                Image resetoni = new Image("BackLayer.png", 50, 50, false, false);
                reset.setImage(resetoni);
                reset.setOnMouseClicked(new LocationPressed(secondX, SecondY, secondImage));
                GridPane.setConstraints(reset, secondX, SecondY);
                game.getChildren().remove(secondImage);
                game.getChildren().add(secondImage, reset);

                ImageView reset1 = new ImageView();
                Image resetoni1 = new Image("BackLayer.png", 50, 50, false, false);
                reset1.setImage(resetoni1);
                reset1.setOnMouseClicked(new LocationPressed(firstX, firstY, firstImage));
                GridPane.setConstraints(reset1, firstX, firstY);
                game.getChildren().remove(firstImage);
                game.getChildren().add(firstImage, reset1);

                firstCard = 0;
            }

            if (flag) {

                ImageView doni = new ImageView();
                Image resetdoni;
                if (whatgame) {
                    resetdoni = new Image(f.getBoard().get(secondImage).getImage(), 50, 50, false, false);
                } else {
                    resetdoni = new Image(f.getChampBoard().get(secondImage).getImage(), 50, 50, false, false);
                }
                doni.setImage(resetdoni);
                GridPane.setConstraints(doni, secondX, SecondY);
                game.getChildren().remove(secondImage);
                game.getChildren().add(secondImage, doni);

                ImageView doni1 = new ImageView();
                Image resetdoni1;
                if (whatgame) {
                    resetdoni1 = new Image(f.getBoard().get(firstImage).getImage(), 50, 50, false, false);
                } else {
                    resetdoni1 = new Image(f.getChampBoard().get(firstImage).getImage(), 50, 50, false, false);
                }
                doni1.setImage(resetdoni1);
                GridPane.setConstraints(doni1, firstX, firstY);
                game.getChildren().remove(firstImage);
                game.getChildren().add(firstImage, doni1);

                firstCard = 0;
                cards++;

                if (turn % 2 == 0) {
                    turn--;
                    System.out.println("player 2");
                    f.addPointP2();
                    game.getChildren().remove(tmpp + 1);
                    Label PlayerTwo = new Label("P2:" + f.getPlayer2point());
                    GridPane.setConstraints(PlayerTwo, 198, (row + row / 2));

                    game.getChildren().add(tmpp + 1, PlayerTwo);
                    System.out.println("PLayer two:" + f.getPlayer2point());

                    Label playerTurn = new Label("Play");
                    playerTurn.setWrapText(true);
                    playerTurn.autosize();
                    playerTurn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
                    GridPane.setConstraints(playerTurn, 198, (row + row / 2) + 10);
                    game.getChildren().remove(tmpp + 2);
                    game.getChildren().add(playerTurn);
                } else {
                    turn--;
                    f.addPointP1();
                    System.out.println("player 1");

                    game.getChildren().remove(tmpp);
                    Label PlayerOne = new Label("P1:" + f.getPlayer1point());
                    GridPane.setConstraints(PlayerOne, 0, (row + row / 2));
                    game.getChildren().add(tmpp, PlayerOne);

                    Label playerTurn = new Label("Play");

                    GridPane.setConstraints(playerTurn, 0, (row + row / 2) + 10);
                    playerTurn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
                    game.getChildren().remove(tmpp + 2);
                    game.getChildren().add(playerTurn);

                }

            }
            if (!flag) {

                if (turn % 2 == 0) {
                    Label playerTurn = new Label("Play");
                    GridPane.setConstraints(playerTurn, 0, (row + row / 2) + 10);
                    playerTurn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
                    game.getChildren().remove(tmpp + 2);
                    game.getChildren().add(playerTurn);
                } else {
                    Label playerTurn = new Label("Play");
                    GridPane.setConstraints(playerTurn, 198, (row + row / 2) + 10);
                    playerTurn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
                    game.getChildren().remove(tmpp + 2);
                    game.getChildren().add(playerTurn);

                }
            }
            if ((cards == sizoe)) {
                scoresP1[0] += 1;

                System.out.println("\nnrOfScores P1: " + scoresP1[0]);
                scoresP1[scoresP1[0]] = f.getPlayer1point();
                System.out.println("\nAfter nrOfScores P1: " + scoresP1[0]);
                nrOfScoresP1++;

                scoresP2[0] += 1;
                System.out.println("\nnrOfScores P2: " + scoresP2[0]);
                scoresP2[scoresP2[0]] = f.getPlayer2point();
                System.out.println("\nAfter nrOfScores P2: " + scoresP2[0]);
                nrOfScoresP2++;

                System.out.println(f.getPlayer1point());
                System.out.println(f.getPlayer2point());

                if (f.getPlayer1point() > f.getPlayer2point()) {
                    Label secondLabel = new Label("PLAYER ONE WON!!!!");
                    GridPane secondLayout = new GridPane();
                    secondLayout.getChildren().add(secondLabel);

                    Scene secondScene = new Scene(secondLayout, 350, 70);

                    Stage newWindow = new Stage();
                    newWindow.setTitle("WHO WON?");
                    newWindow.setScene(secondScene);

                    newWindow.show();
                }
                if (f.getPlayer2point() > f.getPlayer1point()) {
                    Label secondLabel = new Label("PLAYER TWO WON!!!!");
                    GridPane secondLayout = new GridPane();
                    secondLayout.getChildren().add(secondLabel);

                    Scene secondScene = new Scene(secondLayout, 350, 70);

                    Stage newWindow = new Stage();
                    newWindow.setTitle("WHO WON?");
                    newWindow.setScene(secondScene);

                    newWindow.show();
                }
                if (f.getPlayer2point() == f.getPlayer1point()) {
                    Label secondLabel = new Label("DRAW!!");
                    GridPane secondLayout = new GridPane();
                    secondLayout.getChildren().add(secondLabel);

                    Scene secondScene = new Scene(secondLayout, 350, 70);

                    Stage newWindow = new Stage();
                    newWindow.setTitle("WHO WON?");
                    newWindow.setScene(secondScene);

                    newWindow.show();
                }
                try {
                    r.serializeToFile(scoresP1, "hej.txt");
                } catch (IOException e) {
                    System.out.println("opsi");
                }
                try {
                    r.serializeToFile(scoresP2, "hejP2.txt");
                } catch (IOException e) {
                    System.out.println("opsi");
                }
            }

        }

    }

    class ExitPressed implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {

        }
    }

    class GoBackPressed implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            View s = new View();

        }
    }

    public static void main(String[] args) {

        launch(args);

    }

}
