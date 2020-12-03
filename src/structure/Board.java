package structure;

import java.util.ArrayList;
import java.util.Random;



public class Board {
    private ArrayList<Fruit> listOfFruits;
    private int EASY = 8;
    private int MEDIUM = 12;
    private int HARD =14 ;
    private ArrayList<Lolchampions> listOfChamps;
    private Random RAND = new Random();

    
    
    public Board(int size){
         
        fillDifficulty(size);
        fillDifficulty1(size);
        
    }
    public void fillDifficulty1(int size){
        Boolean match = false;
            lolchamps buffert;
            lolchamps[] diffChamps = lolchamps.values();
            ArrayList<Lolchampions> tmp = new ArrayList<>(size);
            this.listOfChamps = new ArrayList<Lolchampions>(size*2);
            for(int i=0;i<size;i++){  
                buffert = diffChamps[RAND.nextInt(diffChamps.length)];
                for(int k =0;k<this.listOfChamps.size();k++){
                    if(this.listOfChamps.get(k).getLolc().equals(buffert)){
                        match = true;
                        
                    }
                }
                 if(!match){
                        this.listOfChamps.add(new Lolchampions(buffert,buffert+".png"));
                    }
                 else if (match){
                     match = false;
                     i--;
                 }
                
                
            }
            tmp.addAll(this.listOfChamps);
            this.listOfChamps.addAll(tmp);
        }
    
    
    public void fillDifficulty(int size){
        Boolean match = false;
            Fruits buffert;
            Fruits[] diffFruits = Fruits.values();
            ArrayList<Fruit> tmp = new ArrayList<>(size);
            this.listOfFruits = new ArrayList<Fruit>(size*2);
            for(int i=0;i<size;i++){  
                buffert = diffFruits[RAND.nextInt(diffFruits.length)];
                for(int k =0;k<this.listOfFruits.size();k++){
                    if(this.listOfFruits.get(k).getFruit().equals(buffert)){
                        match = true;
                        
                    }
                }
                 if(!match){
                        this.listOfFruits.add(new Fruit(buffert,buffert+".png"));
                    }
                 else if (match){
                     match = false;
                     i--;
                 }
                
                
            }
            tmp.addAll(this.listOfFruits);
            this.listOfFruits.addAll(tmp);
        }
    
    public ArrayList<Fruit> getBoard(){
        return this.listOfFruits;
    }
    public ArrayList<Fruit> returnArray(){
        return this.listOfFruits;
    }
    
    public ArrayList<Lolchampions> getChampBoard(){
        return this.listOfChamps;
    }
    

    
    @Override
    public String toString(){
      String info= null;
      int tmp = 0;
      if(this.listOfFruits.size() == EASY*2){
      info = "Easy Memory\nFruits:\n";
              for(int i=0;i<EASY*2;i++){
                  tmp ++;
                  if(tmp == 5){
                      info += "\n";
                      tmp = 0;
                  }
                  info+= this.listOfFruits.get(i)+"\n";
                  
              }
    }
      else if(this.listOfFruits.size() == MEDIUM*2){
      info = "Medium Memory\nFruits:\n";
              for(int i=0;i<MEDIUM*2;i++){
                  info+= this.listOfFruits.get(i)+"\n";
              }
    }
      else if(this.listOfFruits.size() == HARD*2){
      info = "Hard Memory\nFruits:\n";
              for(int i=0;i<HARD*2;i++){
                  info+= this.listOfFruits.get(i)+"\n";
              }
    }
     return info;
    }
}