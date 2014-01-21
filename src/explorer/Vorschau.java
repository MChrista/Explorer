package explorer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author A001278
 */
public class Vorschau extends JPanel{
	
    private JLabel label;
    private BufferedImage image;
    private ImageIcon icon;
    private final double MAX_WIDTH = 200;
	
	public Vorschau() {
		this.setLayout(new BorderLayout());
    	label = new JLabel("<html>Keine Datei zur<br>Vorschau ausgewählt</html>");
    	this.add(label, BorderLayout.CENTER);
    	
    	this.setBackground(Color.GRAY); //Nur um Layout Bereiche/Grenzen zu sehen
    }
	
	// Beim Auwählen/Markieren einer Datei aufgerufen
	// Zeigt eine Vorschau der übergebenen Datei an
    public void reload(File file) {
    	String fname = file.getName();
    	if (fname.endsWith(".jpg") || fname.endsWith(".png") || fname.endsWith(".gif")) {
    		try {
    		image = ImageIO.read(file);
    		if (image.getWidth() > MAX_WIDTH) {
    			// image verkleinern, da zu breit
    			double faktor = MAX_WIDTH / image.getWidth();	// Um faktor verkleinern
    			AffineTransformOp atop = new AffineTransformOp(AffineTransform.getScaleInstance(faktor, faktor), AffineTransformOp.TYPE_BICUBIC);
    			BufferedImage imgOut = atop.createCompatibleDestImage(image, ColorModel.getRGBdefault());
    			atop.filter(image, imgOut);
    			image = imgOut;
    			System.out.println("Bild wurde auf " + faktor*100 + "% verkleinert.");
    		}
    		label.setText(null);
    		icon = new ImageIcon(image);
    		label.setIcon(icon);
    		}
    		catch(Exception e) {
    			System.err.println("Bild kann nicht gelesen werden:");
    			e.printStackTrace();
    		}
    	}
    }
}