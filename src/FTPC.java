//Notr that is class references the packages from
//http://commons.apache.org/proper/commons-net/download_net.cgi

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


public class FTPC {
	private String server;
	private int port = 21;
	private String user;
	private String pass;
	private File a = new File("");
	FTPClient ftpClient = new FTPClient();
	public FTPC(String ip,String u,String p, File f) {//I modified the code based on my need and the example program ofered by the package
		server = ip;
		port = 21;
		user = u;
		pass = p;
		a = f;
	}
	//the only method will upload the file to the server
	public void upload(){
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			File firstLocalFile = a;
			Date date = new Date();
			String firstRemoteFile = "NumGenlog-"+date+".txt";
			InputStream inputStream = new FileInputStream(firstLocalFile);

			System.out.println(date+":Start uploading log file");
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
			inputStream.close();
			if (done) {
				System.out.println(date+":The log file is uploaded successfully.");
			}
		}catch (IOException ex) {
	            System.out.println(":Error: " + ex.getMessage());
		}
	}
}