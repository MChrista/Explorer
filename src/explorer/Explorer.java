/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package explorer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author A001278
 */
public class Explorer extends JFrame{
    Ordnerstruktur bst=new Ordnerstruktur();
    public Ordnerstruktur getBst() {
		return bst;
	}



	public void setBst(Ordnerstruktur bst) {
		this.bst = bst;
	}



	public Dateistruktur getDst() {
		return dst;
	}



	public void setDst(Dateistruktur dst) {
		this.dst = dst;
	}



	public Vorschau getVor() {
		return vor;
	}



	public void setVor(Vorschau vor) {
		this.vor = vor;
	}



	Dateistruktur dst=new Dateistruktur(this);
    Vorschau vor=new Vorschau(this);
    
    public Explorer(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,3));
        this.add(bst);
        this.add(dst);
        this.add(vor);
        this.setVisible(true);
        
        
        //Andi: Vorschau-Test
        this.setBounds(100, 100, 600, 400);
        //vor.reload(new File("Testdateien/square100.jpg"));
        //vor.reload(new File("Testdateien/square200.png"));
        //vor.reload(new File("Testdateien/square400.gif"));
        //vor.reload(new File("Testdateien/square800.png"));
        //vor.reload(new File("Testdateien/200x500.png"));
        //vor.reload(new File("Testdateien/spannend.txt"));
        //vor.reload(new File("Testdateien/falsche.endung"));
        vor.reload(new File("Testdateien/test.html"));
        //vor.reload(new File("Testdateien/bigblub.rtf"));
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Explorer();
        // TODO code application logic here
    }
    
}
