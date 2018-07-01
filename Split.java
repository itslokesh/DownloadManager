package FInal;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;

import javax.swing.JProgressBar;

public class Split implements Runnable{
	private int block;
	private int size;
	private InputStream is;
	private byte[][] buffer;
	private HttpURLConnection c;
	private String url;
	private Status s;
	
	public Split(int start,int size,byte[][] buffer,String url){
		this.block=start;
		this.size=size;
		this.buffer=buffer;
		this.url=url;
		URL u;
		try {
			u = new URL(url);
			c=(HttpURLConnection) u.openConnection();
			is=c.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch(IOException e1){
		}
		
	}
	
	public void run(){
			try {
				int acc=0,tot=c.getContentLength();
				is.skip(block*size);
				byte[] r=new byte[1];
				for(int i=0;i<size;i++){
					System.out.println("Thread- "+block+" Byte number :"+(block*size+i));
					is.read(r);
					Downloader.count++;
					int temp=(int)java.lang.Math.ceil((Downloader.count*100)/(8.0*size));
					buffer[block][i]=r[0];
				}
				} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
