package ModelViewController;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MemoryController {
    
    public MemoryController(){
        
    }
       
    
    static class OnePlayerPressed implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            MemoryLogic s = new MemoryLogic(4);
          
            System.out.println(""+s.toString());
        }

    
}
    
    static class TwoPlayerPressed implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            System.out.println("hej\n");

        }
}
    static class firstLocationPressed implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
        Image back = new Image("BackLayer.png");
        ImageView firstLocation = new ImageView();
        firstLocation.setImage(back);
        

        }
}
    
    
    static class ExitPressed implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            
        }
}
     static class GoBackPressed implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            View s = new View();
            
        }
}
    
    
}
