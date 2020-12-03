
package testing;
import java.util.ArrayList;
import ModelViewController.MemoryLogic;
import java.util.Scanner;
import structure.Board;
import structure.Difficulties;
import structure.Fruit;
import structure.Fruits;



public class TestMain {
    
    public static void main(String[] args) {
//     Board s = new Board(Difficulties.EASY);
//     System.out.println(""+s.toString());
        MemoryLogic y = new MemoryLogic(8,0);
        Scanner scan = new Scanner (System.in);
        
        System.out.println("Points:" + y.getPlayer1point());
        y.addPointP1();
        System.out.println("Points:" + y.getPlayer1point());        
        System.out.println(""+y.toString());
        
        System.out.println("Flip card: ");
        int place = scan.nextInt();       
        
        System.out.println("Flip 2nd card: ");
        int place1 = scan.nextInt();             
        
        y.flipAndCompare(place, place1);
        
        System.out.println("Points:" + y.getPoints());
        System.out.println(""+y.toString());       
         



        
        
    }
    
}
