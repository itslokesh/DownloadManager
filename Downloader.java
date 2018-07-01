package FInal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader {
	public static int count=0;
	
    public static void downloadFile(String fileURL, String saveDir)throws IOException {
		//System.setProperty("http.proxyHost", "172.17.0.1");
		//System.setProperty("http.proxyPort", "3128");
    	URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
        count=0;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            if (disposition != null) {
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 9,disposition.length());
                }
            }
            else {
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,fileURL.length());
            }
            System.out.println("Size = " + httpConn.getContentLength()+" bytes");
            //fileName="TCS.pdf";
            System.out.println("FileName = " + fileName);
            InputStream inputStream = httpConn.getInputStream();
            int size=(int)java.lang.Math.ceil(httpConn.getContentLength()/8.0);
            Thread t[]=new Thread[9];
            Split t1[]=new Split[8];
            byte[][] buffer=new byte[8][size];
            for(int i=0;i<8;i++){
            	t1[i]=new Split(i,size,buffer,fileURL);
            	t[i]=new Thread(t1[i]);
            	t[i].setPriority(10);
            }
            t[7].setPriority(1);
            for(int i=0;i<8;i++){
            	t[i].start();
            }
            try {
				t[7].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            String saveFilePath = saveDir + File.separator + fileName;
            OutputStream outputStream = new FileOutputStream(saveFilePath);
            byte[] r=new byte[1];
            for(int i=0;i<8;i++){
            	outputStream.write(buffer[i],0,size);
            }
            outputStream.close();
            inputStream.close();
            System.out.println("File downloaded");
        }
        else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
}