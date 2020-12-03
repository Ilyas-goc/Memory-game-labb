/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

/**
 *
 * @author Ilyas
 */
public class Lolchampions {
    private lolchamps lolc;
    private String image;
    
    public Lolchampions(lolchamps lolc,String image){
        this.lolc = lolc;
        this.image = image;
    }

    /**
     * @return the lolc
     */
    public lolchamps getLolc() {
        return lolc;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }
}
