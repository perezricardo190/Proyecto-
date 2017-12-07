

package cy.com.Downloading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import android.os.Handler;

public class DownloadingSettings extends Thread {

	private final String IP;
	private final String PORT;
	private final String filename_radiomap_download;
	private final String folder_path;

	private String errMsg;
	private final Handler handler;

	/**
	 * @param IP
	 *            the IP address of radiomap distribution server
	 * 
	 * @param PORT
	 *            the port that radiomap distribution server is listening
	 * */
	public DownloadingSettings(String IP, String PORT, String folder_path, String filename, Handler handler) {
		this.filename_radiomap_download = filename;
		this.PORT = PORT;
		this.IP = IP;
		this.folder_path = folder_path;
		this.handler = handler;
	}

	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * Establishes a connection on IP/PORT and download radiomap
	 * 
	 * */
	public void run() {

		Socket connection = null;
		FileOutputStream fos = null;
		File root = new File(folder_path);

		String radiomap_mean = filename_radiomap_download;
		String parameters = radiomap_mean + "-parameters";

		try {

			// Create new socket
			connection = new Socket(IP, Integer.parseInt(PORT));

			// Check that path is writable
			if (root.canWrite()) {
				fos = new FileOutputStream(new File(root, radiomap_mean), false);
			} else {
				errMsg = "Directory: " + root.getAbsolutePath() + " is not writable.\nYou may need an external memory card";
				handler.sendEmptyMessage(-2);
				return;
			}

			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine, outputLine;

			inputLine = in.readLine();

			if (!inputLine.equalsIgnoreCase("+OK READY")) {
				fos.close();
				out.close();
				in.close();
				connection.close();
				errMsg = "Server not ready.\nTry again later.";
				handler.sendEmptyMessage(-2);
				return;
			}

			outputLine = "GET radiomap";
			out.println(outputLine);
			inputLine = in.readLine();

			if (!inputLine.startsWith("RADIOMAP")) {
				fos.close();
				out.close();
				in.close();
				connection.close();
				errMsg = inputLine + "";
				handler.sendEmptyMessage(-2);
				return;
			}

			inputLine = inputLine.replaceFirst("RADIOMAP ", "");
			fos.write((inputLine + "\n").getBytes());

			while ((inputLine = in.readLine()) != null) {
				if (inputLine.compareTo("null") == 0 || inputLine.startsWith("CORRUPTED"))
					break;

				if (inputLine.equalsIgnoreCase("PARAMETERS")) {
					fos = new FileOutputStream(new File(root, parameters), false);
				} else {
					fos.write((inputLine + "\n").getBytes());
				}
			}

			fos.close();
			out.close();
			in.close();
			connection.close();

			errMsg = null;
			handler.sendEmptyMessage(-2);

		} catch (Exception e) {
			errMsg = "Error: " + e.getMessage();
			handler.sendEmptyMessage(-2);
		}

	}

}
