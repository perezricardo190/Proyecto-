
package radiomapserver;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ServerMode extends Thread {

    private volatile boolean listening = true;
    private final int ServerPort;
    private volatile ServerSocket ListenSocket;
    private Socket Connection;
    private final File filename;
    private final File parameters_file;
    private final File RSSFolder;
    private final JTextArea logDisplay;
    private final String string_mode;
    private boolean running = false;
    private final JTextField state;
    private final DefaultTableModel model;

    public enum STATES {

        NOT_RUNNING, RUNNING, STOPPED, FILE_NOT_FOUND, ERROR;

        public static String toString(STATES st) {

            switch (st) {
                case NOT_RUNNING:
                    return "Not running";
                case RUNNING:
                    return "Running";
                case STOPPED:
                    return "Stopped";
                case ERROR:
                    return "Error";
                case FILE_NOT_FOUND:
                    return "File not found";
                default:
                    return "Error";
            }
        }
    }

    public enum MODE {

        INDOOR, OUTDOOR
    }
    private final MODE mode;

    public ServerMode(int Port, String RSSFolder, File filename, MODE m, JTextArea logDisplay, JTextField state, DefaultTableModel model) {
        this.ServerPort = Port;
        this.filename = filename;
        this.RSSFolder = new File(RSSFolder);
        this.mode = m;
        this.logDisplay = logDisplay;
        this.model = model;

        if (mode == MODE.INDOOR) {
            string_mode = "Indoor";
        } else {
            string_mode = "Outdoor";
        }

        this.parameters_file = new File(filename.getAbsolutePath().replace("-mean.txt", "-parameters.txt"));
        this.state = state;
        state.setText(STATES.toString(STATES.NOT_RUNNING));
    }

    @Override
    public void run() {

        logDisplay.setText("");

        checkDistributionFiles();


        logDisplay.append("Starting " + string_mode + " Radio Map module\n");

        try {
            InetAddress here = InetAddress.getLocalHost();
            ListenSocket = new ServerSocket(ServerPort);

            logDisplay.append(string_mode + " Radio Map module started on " + here.getHostName() + " with IP:PORT [" + here.getHostAddress() + ":" + ServerPort + "]\n");

            // wait while clients are connected
            logDisplay.append("Listening for connections [" + string_mode + " mode]...\n\n");

            running = true;
            state.setText(STATES.toString(STATES.RUNNING));
            while (listening) {
                try {
                    Connection = ListenSocket.accept();
                    logDisplay.append("Connection request received from: " + Connection.getInetAddress().getHostName() + "\n");
                    MultiServerThread thread = new MultiServerThread(Connection, RSSFolder, filename, parameters_file, model);
                    thread.start();
                } catch (Exception e) {
                    logDisplay.append("Stopped listening for connections");
                }
            }// end of while(true)
        } catch (UnknownHostException uhe) {
            logDisplay.append("ERROR: " + uhe.getMessage() + "\n");
            state.setText(STATES.toString(STATES.ERROR));
        } catch (IOException ioe) {
            logDisplay.append("ERROR: " + ioe.getMessage() + "\n");
            state.setText(STATES.toString(STATES.ERROR));
        } catch (IllegalArgumentException iae) {
            logDisplay.append("ERROR: " + iae.getMessage() + "\n");
            state.setText(STATES.toString(STATES.ERROR));
        } catch (Exception e) {
            logDisplay.append("ERROR: " + e.getMessage() + "\n");
            state.setText(STATES.toString(STATES.ERROR));
        }
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void stopRunning() {
        listening = false;
        try {
            ListenSocket.close();
            logDisplay.setText("");
        } catch (IOException ex) {
            logDisplay.append("ERROR: " + ex.getMessage() + "\n");
        }
        state.setText(STATES.toString(STATES.STOPPED));
    }

    private void checkDistributionFiles() {

        if (!filename.exists() || !filename.isFile()) {
            JOptionPane.showMessageDialog(null, "Could not find " + filename.getAbsolutePath() + " for distribution.\nCreate one first!\n", "Warning", JOptionPane.WARNING_MESSAGE);
            logDisplay.setText(filename.getAbsolutePath() + " not found for " + string_mode + " distribution\n");
            return;
        }

        if (!parameters_file.exists() || !parameters_file.isFile()) {
            JOptionPane.showMessageDialog(null, "Could not find " + parameters_file.getAbsolutePath() + " for distribution.\nCreate one first!\n", "Warning", JOptionPane.WARNING_MESSAGE);
            logDisplay.setText(parameters_file.getAbsolutePath() + " not found for " + string_mode + " distribution\n");
            return;
        }
    }
}
