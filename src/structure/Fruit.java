
package structure;

import java.util.ArrayList;


public class Fruit {
    
   
    private Fruits fruit;
    private String image;


    
    public Fruit(Fruits fruit,String image){
       this.fruit= fruit;
       this.image = image;
    }
    


    public String getImage(){
       
        return this.image;
    }
    
    public Fruits getFruit(){
        return this.fruit;
    }
    
    
    @Override
    public String toString(){
        String info= ""+ this.getFruit();
        
        return info;
    }
    
   
}
