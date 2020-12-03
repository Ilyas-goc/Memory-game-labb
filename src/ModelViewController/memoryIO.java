/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelViewController;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author Ilyas
 */
public class memoryIO {
                public void serializeToFile(int[] scores,String filename) throws IOException {
		
		ObjectOutputStream out = null;
		
		try {
			out = new ObjectOutputStream(
			new FileOutputStream(filename));
			out.writeObject(scores);
		}
		finally {
			try {
				if(out != null)	out.close();
			} catch(IOException e) {}
		}
	}
        	public int[] deSerializeFromFile(int[] scores,String filename) throws IOException, ClassNotFoundException {
		
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream(filename));
			// readObject returns a reference of type Object, hence the down-cast
                        int[] array = (int[]) in.readObject();
			return array;
                        
		}       
                
                
		finally {
			try {
				if(in != null)	in.close();
			} catch(IOException e) {}			
		}
	}
}
