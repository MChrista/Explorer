/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package explorer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author A001278
 */
public class Explorer extends JFrame{
    JLabel ordner=new JLabel("Ordner");
    JLabel datei=new JLabel("Datei");
    JLabel vorschau=new JLabel("Vorschau");
    Baumstruktur bst=new Baumstruktur();
    Dateistruktur dst=new Dateistruktur();
    Vorschau vor=new Vorschau();
    
    public Explorer(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JPanel pane=new JPanel();
        pane.setLayout(new GridLayout(1,3));
        pane.add(ordner);
        pane.add(datei);
        pane.add(vorschau);
        this.add(pane,BorderLayout.NORTH);
        this.add(bst,BorderLayout.WEST);
        this.add(dst,BorderLayout.CENTER);
        this.add(vor,BorderLayout.EAST);
        this.setVisible(true);
        
        
        
        this.setBounds(100, 100, 500, 400);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Explorer();
        // TODO code application logic here
    }
}