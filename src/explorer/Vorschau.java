package explorer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.text.rtf.RTFEditorKit;


/**
 *
 * @author Andreas Pregler
 */
public class Vorschau extends JPanel{
	
	private Explorer explorer;
    private JLabel label;
    private TextArea area;
    private JScrollPane scrollPane;
    private JEditorPane editorPane;
    private BufferedImage image;
    private ImageIcon icon;
    private int max_width = 200;
    private int max_height = 356;
	
	public Vorschau(Explorer expl) {
		explorer = expl;
		this.setLayout(new BorderLayout());
    	label = new JLabel("<html>Keine Datei zur<br>Vorschau ausgewählt.</html>");
    	area = new TextArea(null, 1, 20, TextArea.SCROLLBARS_BOTH); //xxx Zahl 20 noch aus möglicher Breite berechnen?
    	area.setEditable(false);
    	editorPane = new JEditorPane();
		editorPane.setEditable(false);
        scrollPane = new JScrollPane(editorPane);
    	this.add(label, BorderLayout.CENTER);
    	this.setBackground(Color.GRAY); //xxx Nur um Layout Bereiche/Grenzen zu sehen
    }
	
	// Beim Auwählen/Markieren einer Datei aufgerufen
	// xxx Beim Veränern der Fenstergröße aufrufen!
	// Zeigt eine Vorschau der übergebenen Datei an
    public void reload(File file) {
    	this.remove(label);
    	label.setText(null);
    	label.setIcon(null);
    	this.remove(area);
    	this.remove(scrollPane);
    	// xxx max h und w von Explorer geben lassen? get liefert manchmal flasche Werte
    	/*max_width = explorer.getWidth() / 3;
		max_height = this.getHeight();
		System.out.println("frame w: " + explorer.getWidth() + " h: " + explorer.getHeight());
		System.out.println("panel w: " + this.getWidth() + " h: " + this.getHeight());
		System.out.println("max-var w: " + max_width + " h: " + max_height);*/
    	String fname = file.getName();
    	
    	/*--------------------
    	 * Bilddateien
    	 --------------------*/
    	if (fname.endsWith(".jpg") || fname.endsWith(".png") || fname.endsWith(".gif")) {
        	this.add(label, BorderLayout.CENTER);
    		try {
	    		image = ImageIO.read(file);
	    		if (image.getWidth() > max_width) {			// Zu breit
	    			verkleinern((double) max_width / image.getWidth());
	    		}
	    		if (image.getHeight() > max_height) {		// Zu hoch
	    			verkleinern((double) max_height / image.getHeight());
	    		}
	    		icon = new ImageIcon(image);
	    		label.setIcon(icon);
    		}
    		catch(Exception e) {
    			System.err.println("Bild kann nicht gelesen werden:");
    			e.printStackTrace();
    		}
    	}
    	
    	/*--------------------
    	 * Textdateien
    	 --------------------*/
    	else if (fname.endsWith(".txt")) {
        	this.add(area, BorderLayout.CENTER);
			area.setText(txtLesen(file));
    	}
    	
    	/*--------------------
    	 * HTML-Dateien
    	 --------------------*/
    	else if (fname.endsWith(".html") || fname.endsWith(".htm")) {
	        HTMLEditorKit htmlKit = new HTMLEditorKit();
	        editorPane.setEditorKit(htmlKit);
	        Document doc = htmlKit.createDefaultDocument();
	        editorPane.setDocument(doc);
	        editorPane.setText(txtLesen(file));
	        scrollPane.setPreferredSize(new Dimension(max_width, max_height));   
	        this.add(scrollPane, BorderLayout.CENTER);
    	}
    	
    	/*--------------------
    	 * RTF-Dateien
    	 --------------------*/
    	else if (fname.endsWith(".rtf")) {
    		RTFEditorKit rtfKit = new RTFEditorKit();
	        editorPane.setEditorKit(rtfKit);
	        Document doc = rtfKit.createDefaultDocument();
	        editorPane.setDocument(doc);
	        editorPane.setText(txtLesen(file));
	        scrollPane.setPreferredSize(new Dimension(max_width, max_height));   
	        this.add(scrollPane, BorderLayout.CENTER);
    	}
    	
    	/*--------------------
    	 * PDF-Dateien
    	 --------------------*/
    	else if (fname.endsWith(".pdf")) {
    		//xxx
    	}
    	
    	else {
        	this.add(label, BorderLayout.CENTER);
        	label.setText("<html>Vorschau für diesen<br>Dateityp nicht möglich.</html>");
    	}
    	explorer.validate();
    }
    
    // Verkleinert ein Bild auf den übergebenen Faktor
    private void verkleinern(double faktor) {
    	AffineTransformOp atop = new AffineTransformOp(AffineTransform.getScaleInstance(faktor, faktor), AffineTransformOp.TYPE_BICUBIC);
		BufferedImage imgOut = atop.createCompatibleDestImage(image, ColorModel.getRGBdefault());
		atop.filter(image, imgOut);
		image = imgOut;
		System.out.println("Bild wurde auf " + faktor*100 + "% verkleinert.");
    }
    
    private String txtLesen(File file) {
    	StringBuffer buffer = new StringBuffer();
		try {
			FileReader reader = new FileReader(file);
			int c;
			while ((c = reader.read()) != -1) {
				buffer.append((char) c);
			}
			reader.close();
			return buffer.toString();
		}
		catch (IOException e) {
			System.err.println("Datei kann nicht gelesen werden:");
			e.printStackTrace();
		}
		return null;
    }
}