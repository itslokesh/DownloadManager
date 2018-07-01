package FInal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Status extends JFrame{

	public JProgressBar jp;
	
	public Status(int value){
		Container cp=getContentPane();
		cp.setLayout(null);
		jp=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
		jp.setBounds(50,50,400,50);
		jp.setValue(value);
		cp.add(jp);
	}
	
}
