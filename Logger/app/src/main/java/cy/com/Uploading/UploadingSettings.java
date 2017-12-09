

package cy.com.Uploading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

public class UploadingSettings extends Thread {

	private String IP;
	private String PORT;
	private Socket socket = null;
	private String filename;

	private String errMsg;
	private ProgressDialog dialog;

	public UploadingSettings(String IP, String PORT, String filename, ProgressDialog dialog) {
		this.filename = filename;
		this.PORT = PORT;
		this.IP = IP;
		this.dialog = dialog;
	}

	public boolean connect() {
		try {
			socket = new Socket(IP, Integer.parseInt(PORT));
			return true;
		} catch (NumberFormatException e) {
			errMsg = "Specify IP/PORT to upload";
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return false;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void run() {

		try {

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String inputLine, outputLine;

			inputLine = in.readLine();

			if (!inputLine.equalsIgnoreCase("+OK READY")) {
				out.close();
				in.close();
				socket.close();
				errMsg = "Server not available right now";
				handler.sendEmptyMessage(0);
				return;
			}

			outputLine = "UPLOAD rsslog";
			out.println(outputLine);

			inputLine = in.readLine();

			if (!inputLine.equalsIgnoreCase("+OK UPLOAD")) {
				out.close();
				in.close();
				socket.close();
				errMsg = "Server not available right now";
				handler.sendEmptyMessage(0);
				return;
			}

			BufferedReader reader = new BufferedReader(new FileReader(filename));

			while ((outputLine = reader.readLine()) != null) {
				out.println(outputLine);
			}

			reader.close();
			out.close();
			in.close();
			socket.close();
			dialog.dismiss();
			return;

		} catch (IOException e) {
			errMsg = e.getMessage();
			handler.sendEmptyMessage(0);
		}
	}

	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			dialog.dismiss();
		}
	};

}
