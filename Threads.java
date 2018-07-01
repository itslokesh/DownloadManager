package FInal;
import java.io.*;
import java.net.HttpURLConnection;
import java.nio.channels.FileChannel;

public class Threads implements Runnable{
	private int block;
	private int size;
	private InputStream is;
	private byte[][] buffer;
	private long check[];
	private HttpURLConnection c;
	private int downloaded;
	
	public Threads(int start,int size,HttpURLConnection c,byte[][] buffer,long check[]){
		this.block=start;
		this.size=size;
		this.c=c;
		this.buffer=buffer;
		this.check=check;
	}
	
	public void run(){
			downloaded=block*size;
			try {
				byte[] r=new byte[1];
				for(int i=0;i<size;i++){
					is=c.getInputStream();
					is.skip(downloaded);
					is.read(r);
					buffer[block][i]=r[0];
					downloaded++;
					System.out.println("Thread "+block+" Byte :"+downloaded);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
