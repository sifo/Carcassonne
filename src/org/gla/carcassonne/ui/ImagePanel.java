package org.gla.carcassonne.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public BufferedImage bufferedImage;
	private int width;
	private int height;
	public String fileString;

	public ImagePanel() {
		width = 80;
		height = 80;
		bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public ImagePanel(int width, int height) {
		this.width = width;
		this.height = height;
		bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public ImagePanel(String filePath) {
		this.fileString = filePath;
		File fileImg = new File(filePath);
		try {
			bufferedImage = ImageIO.read(fileImg);
		} catch (IOException e) {
			bufferedImage = null;
			System.err.println("Fichier invalide : " + filePath);
		}
		setVisible(true);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(bufferedImage, 0, 0, width, height, null);
	}

	public void clearPanel(){
		bufferedImage.getGraphics().setColor(Color.GRAY);
		bufferedImage.getGraphics().fillRect(0, 0, width, height);
		this.repaint();
	}
	
	public void setImage(String file) {
		this.fileString = file;
		File fileImg = new File(file);
		try {
			bufferedImage = ImageIO.read(fileImg);
		} catch (IOException e) {
			bufferedImage = null;
			System.err.println("Fichier invalide : " + file);
		}
		this.repaint();
	}
	
	 public void rotate(double scale)
	   {
	      double sinA = Math.sin(scale);
	 
	      AffineTransform tx = new AffineTransform();
	      tx.rotate(scale, this.bufferedImage.getWidth() / 2, this.bufferedImage.getHeight() / 2);
	      AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	 
	      Rectangle rect = op.getBounds2D(this.bufferedImage).getBounds();
	      tx.translate(sinA * rect.getX(), -sinA * rect.getY());
	      op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
	      this.bufferedImage = op.filter(this.bufferedImage, op.createCompatibleDestImage(this.bufferedImage, null) );
	 
	 
	      this.setBounds( getX(), getY(), bufferedImage.getWidth(), bufferedImage.getHeight()  );
	      this.width = bufferedImage.getWidth();
	      this.height = bufferedImage.getHeight();
	   }
	 
	 public ImagePanel getClone(){
		 return new ImagePanel(fileString);
	 }
}
