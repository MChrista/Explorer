package explorer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.rtf.RTFEditorKit;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;


/**
 *
 * @author Andreas Pregler
 */
public class Vorschau extends JPanel {
	
	private Explorer explorer;
	private File file;
	private String fname = "";
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
		this.addComponentListener(new VorschauListener(this));
    	label = new JLabel("<html>Keine Datei zur<br>Vorschau ausgewählt.</html>");
    	area = new TextArea(null, 0, 0, TextArea.SCROLLBARS_BOTH);
    	area.setEditable(false);
    	editorPane = new JEditorPane();
		editorPane.setEditable(false);
        scrollPane = new JScrollPane(editorPane);
    	this.add(label, BorderLayout.CENTER);
    }
	
	// Beim Auwählen/Markieren einer Datei aufgerufen
	// Beim Verändern der Fenstergröße aufgerufen
	// Zeigt eine Vorschau der übergebenen Datei an
    public void reload(File f) {
    	if (f != null) {
    		file = f;
        	fname = file.getName();
    	}
    	this.remove(label);
    	label.setText(null);
    	label.setIcon(null);
    	this.remove(area);
    	this.remove(scrollPane);
    	max_width = this.getWidth();
		max_height = this.getHeight();
    	
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
			area.setText(txtLesen(file));
			area.setPreferredSize(new Dimension(max_width, max_height));
    		this.add(area, BorderLayout.CENTER);
			area.repaint();
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
    		this.add(label, BorderLayout.CENTER);
    		try {
    			PDDocument doc = PDDocument.load(file);
    			List <PDPage> pages = doc.getDocumentCatalog().getAllPages(); 
    			PDPage page = pages.get(0); 
    			image = page.convertToImage(); 
    			if (image.getWidth() > max_width) {			// Zu breit
	    			verkleinern((double) max_width / image.getWidth());
	    		}
	    		if (image.getHeight() > max_height) {		// Zu hoch
	    			verkleinern((double) max_height / image.getHeight());
	    		}
	    		icon = new ImageIcon(image);
	    		label.setIcon(icon);
	    		doc.close();
    		} 
    		catch (Exception e) { 
    			System.err.println("PDF-Datei kann nicht gelesen werden:");
    			e.printStackTrace();
    		} 
    	}
    	
    	/*--------------------
    	 * Ungültige Dateien
    	 --------------------*/
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
		//System.out.println("Bild wurde auf " + faktor*100 + "% verkleinert.");
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
			return null;
		}
    }
}