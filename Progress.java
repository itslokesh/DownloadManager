package FInal;

import javax.swing.JProgressBar;

import com.sun.xml.internal.ws.api.server.Container;

public class Progress implements Runnable{

	private int size;
	private Status s;
	
	public Progress(int size,Status s){
		this.size=size;
		s=new Status(0);
		s.setVisible(true);
		s.setSize(600,300);
	}
	
	public void run(){
		while(Downloader.count<size){
			System.out.println("\tHELLO WORLD");
			int temp=(int)java.lang.Math.ceil((Downloader.count*100)/(8.0*size));
			s.remove(s.jp);
			s.jp=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
			s.jp.setValue(temp);
			s.jp.setBounds(50,50,400,50);
			s.getContentPane().add(s.jp);
		}
		System.out.println("\t\tOVER");
	}
}
