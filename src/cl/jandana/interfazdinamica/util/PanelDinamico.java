package cl.jandana.interfazdinamica.util;



import cl.jandana.interfazdinamica.logica.Matriz;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Javier
 */
public class PanelDinamico extends JPanel {

    private BufferedImage img = null;
    Font arialFont = new Font("Arial", Font.BOLD, 12);
    Matriz matriz;

    public PanelDinamico() {

    }

    @Override
    public void paint(Graphics g) {
        if (img == null) {
            super.paint(g);
        } else {
            g.drawImage(img, 0, 0, this);
        }
    }

    public void render(Matriz matriz, int iteration) {
        float columnas = (float) getWidth() / (float) matriz.getColumnas();
        float filas = (float) getHeight() / (float) matriz.getFilas();     
        float r, g, b;
        Graphics2D g2 = img.createGraphics();        
        for (int x = 0; x < matriz.getColumnas(); x++) {
            for (int y = 0; y < matriz.getFilas(); y++) {
                
                r = (float) ((Double) matriz.getNodo(x, y).getVector().elementAt(0)).doubleValue();
                g = (float) ((Double) matriz.getNodo(x, y).getVector().elementAt(1)).doubleValue();
                b = (float) ((Double) matriz.getNodo(x, y).getVector().elementAt(2)).doubleValue();
                g2.setColor(new Color(r, g, b));
                g2.fillRect((int) (x * columnas), (int) (y * filas),
                        (int) columnas, (int) filas);
               //borde negro
                g2.setColor(Color.BLACK);
                g2.drawRect((int) (x * columnas), (int) (y * filas),
                        (int) columnas, (int) filas);
            }
        }
        g2.setColor(Color.black);
        g2.setFont(arialFont);
     
        g2.drawString("Iteration: " + String.valueOf(iteration), 10, 15);
        repaint();
    }

    public BufferedImage getImagen() {
        if (img == null) {
            img = (BufferedImage) createImage(getWidth(), getHeight());
        }
        return img;
    }

}
