/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package explorer;

import java.awt.GridLayout;

import javax.swing.JFrame;

/**
 *
 * @author A001278
 */
public class Explorer extends JFrame{


	Dateistruktur dst=new Dateistruktur(this);
    Ordnerstruktur ost=new Ordnerstruktur(this);
    Vorschau vor=new Vorschau(this);
    
    public Explorer(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,3));
        this.add(ost);
        this.add(dst);
        this.add(vor);
        this.setBounds(0, 0, 600, 400);
        this.setVisible(true);
    }
   
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Explorer();
        // TODO code application logic here
    }
    
    public Dateistruktur getDst() {
		return dst;
	}


	public Vorschau getVor() {
		return vor;
	}

    
}
