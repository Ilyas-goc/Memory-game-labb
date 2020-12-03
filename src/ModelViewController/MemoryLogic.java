package ModelViewController;

import java.util.ArrayList;
import structure.Board;
import structure.Difficulties;
import structure.Fruit;
import structure.Fruits;
import structure.Lolchampions;

public class MemoryLogic {

    private Board stackOfFruits;
    private Board stackOfChamps;
    private ArrayList<Lolchampions> boardOfChamps;
    ArrayList<Fruit> board;
    ArrayList<Lolchampions> board1;
    private int points = 100;
    private int tmp;
    private int player1point;
    private int player2point;
    private int[] scoresP1;
    private int[] scoresP2;
    private int sizoe = 8;
    private int nrOfScoresP1, nrOfScoresP2;
    private boolean whatgame;

    public MemoryLogic(int size, int theme) {
        initNewGame(size, theme);
    }

    public void initNewGame(int size, int theme) {
        if (theme == 1) {
            whatgame = true;
            this.stackOfFruits = new Board(size);
            this.board = stackOfFruits.getBoard();
        }
        if (theme == 0) {
            whatgame = false;
            this.stackOfChamps = new Board(size);
            this.board1 = stackOfChamps.getChampBoard();
        }
        this.player1point = 0;
        this.player2point = 0;

        scoresP1 = new int[10];
        scoresP2 = new int[10];
    }

    public ArrayList<Fruit> getBoard() {
        ArrayList<Fruit> tmp = new ArrayList<Fruit>();
        for (int i = 0; i < board.size(); i++) {
            tmp.add(board.get(i));
        }
        return tmp;
    }

    public boolean getGame() {
        return whatgame;
    }

    public ArrayList<Lolchampions> getChampBoard() {
        ArrayList<Lolchampions> tmp = new ArrayList<Lolchampions>();
        for (int i = 0; i < board1.size(); i++) {
            tmp.add(board1.get(i));
        }
        return tmp;
    }

    public void addPointP1() {
        player1point++;
    }

    public void addPointP2() {
        player2point++;
    }

    public int getPlayer1point() {
        return this.player1point;
    }

    public int getPlayer2point() {
        return this.player2point;
    }

    public Boolean flipAndCompare(int place, int place1) {
        ArrayList<Fruit> tmp = new ArrayList<Fruit>();
        ArrayList<Lolchampions> tmp1 = new ArrayList<Lolchampions>();
        Boolean match = false;
        Boolean game = getGame();
        if (place != place1) {
            if (game) {
                for (int i = 0; i < board.size(); i++) {
                    tmp.add(board.get(i));
                }
                if (tmp.get(place).equals(tmp.get(place1))) {
                    System.out.println("Vi fick rätt!");
                    points++;
                    match = true;
                } else {
                    System.out.println("wrong");
                }
            } else {
                for (int i = 0; i < board1.size(); i++) {
                    tmp1.add(board1.get(i));
                }
                if (tmp1.get(place).equals(tmp1.get(place1))) {
                    System.out.println("Vi fick rätt!");
                    points++;
                    match = true;
                } else {
                    System.out.println("wrong");
                }
            }
        }
        return match;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        String info = "" + this.board;
        int tmp = 0;
        info = "Easy Memory\n\nFruits:\n";
        for (int i = 0; i < board.size(); i++) {
            if (tmp == 4) {
                info += "\n";
                tmp = 0;
            }
            tmp++;
            info += this.board.get(i) + ", ";
        }
        return info;
    }
}
