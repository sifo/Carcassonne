package org.gla.carcassonne.ui;

import javax.swing.JFrame;
import javax.swing.JProgressBar;


public class ProgressBarFrame extends Thread{
	
	JFrame progressFrame;
	
	public void run() {
		JProgressBar progress = new JProgressBar();
		progress.setIndeterminate(true);
		progress.setVisible(true);

		progressFrame = new JFrame();
		progressFrame.add(progress);
		progressFrame.pack();
				
	}
	
	public void setVisible(boolean b){
		progressFrame.setVisible(b);
	}

}
