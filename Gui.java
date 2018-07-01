package FInal;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Gui extends JFrame{
	private JButton b;
	private JLabel title;
	private JTextField t;

	public Gui(){
		this.setTitle("INTERNET DOWNLOAD MANAGER");
		Container cp=getContentPane();
		cp.setLayout(null);
		cp.setBackground(Color.ORANGE);
		title=new JLabel("Copy and paste a URL in the box and click Download to download the file");
		title.setBounds(100,150,1000,200);
		title.setFont(new Font("Monotype Corsiva",Font.BOLD,30));
		title.setForeground(Color.RED);
		cp.add(title);
		t=new JTextField(50);
		t.setFont(new Font("Comic Sans MS",Font.BOLD,30));
		t.setBounds(100,300,1000,50);
		t.setBackground(Color.BLACK);
		t.setForeground(Color.WHITE);
		cp.add(t);
		b=new JButton("Download");
		b.setBounds(1150,300,200,50);
		b.setBackground(Color.BLUE);
		b.setForeground(Color.YELLOW);
		b.setContentAreaFilled(false);
		b.setOpaque(true);
		b.setFont(new Font("Lucida Handwriting",Font.BOLD,20));
		b.addActionListener(new AL());
		cp.add(b);
	}
	
	class AL implements ActionListener{
		public void actionPerformed(ActionEvent a){
			String fileURL=t.getText();
			String saveDir = "C:\\Users\\ARUN\\Downloads\\SHAREit";
	        try {
	            Downloader.downloadFile(fileURL, saveDir);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		}
	}
}
