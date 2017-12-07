
package radiomapserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class MultiServerThread extends Thread {

    private Socket socket = null;
    private final File filename;
    private final File parameters_file;
    private final File RSSFolder;
    private final DefaultTableModel model;
    private int row;

    MultiServerThread(Socket connection, File RSSFolder, File filename, File parameters_file, DefaultTableModel model) {
        this.socket = connection;
        this.filename = filename;
        this.parameters_file = parameters_file;
        this.RSSFolder = RSSFolder;
        this.model = model;
    }

    @Override
    public void run() {

        addConnection();

        try {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine, outputLine;

            setStatus("Pending");

            // Create a new protocol to exchange data with client
            RadioMapProtocol rmp = new RadioMapProtocol(filename, parameters_file, RSSFolder);

            // Sending "+OK READY"
            outputLine = rmp.processInput(null);
            System.out.println("Send: " + outputLine);
            out.println(outputLine);

            // Repeat until receive a "GET radiomap" or "UPLOAD rsslog" command
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Receive: " + inputLine);
                outputLine = rmp.processInput(inputLine);
                out.println(outputLine);
                System.out.println("Send: " + outputLine);
                // Continue. Received a recognized command, a busy message or unavailable message
                if (!outputLine.startsWith("ERROR")) {
                    break;
                }
            }

            // Close connection if we send a busy message or unavailable message
            if (rmp.isCompleted()) {
                System.out.println("Closed");
                out.close();
                in.close();
                socket.close();
                // Status is now incompleted
                setStatus("Incompleted");
                //removeConnection();
                return;
            }

            // Sending Radio map state
            if (RadioMapProtocol.STATES.SENDING_RADIOMAP == rmp.getState()) {

                // Connection type is sending files
                setConnectionType("Sending radio map files");

                // Data is radio map mean
                setDataExchange("Radio map mean file");

                // Send files
                outputLine = rmp.processInput(null);
                while (!rmp.isCompleted()) {
                    out.println(outputLine);
                    System.out.println("Send: " + outputLine);
                    outputLine = rmp.processInput(null);

                    // Corrupted file
                    if (outputLine != null && outputLine.startsWith("CORRUPTED")) {
                        setStatus("Corrupted");
                        out.println(outputLine);
                        System.out.println("Send: " + outputLine);
                    } // Change data type to Parameters file
                    else if (outputLine != null && outputLine.startsWith("PARAMETERS")) {
                        setDataExchange("Parameters file");
                    } // Change data type to RBF Weights file
                    else if (outputLine != null && outputLine.startsWith("RBF_WEIGHTS")) {
                        setDataExchange("RBF Weights file");
                    }
                }
            } // Retrieving an RSS log file
            else {

                // Connection type is downloading log file
                setConnectionType("Downloading log file");

                // Data is log file
                setDataExchange("Log file");

                // Get RSS log file
                while ((inputLine = in.readLine()) != null && !rmp.isCompleted()) {
                    System.out.println("Receive: " + inputLine);
                    outputLine = rmp.processInput(inputLine);

                    // Corrupted log file
                    if (outputLine != null && outputLine.startsWith("ERROR")) {
                        setStatus("Error");

                        // Error message
                        out.println(outputLine);
                        System.out.println("Send: " + outputLine);
                    }
                }
                // To close the file
                outputLine = rmp.processInput(null);
            }

            System.out.println("Closed");
            out.close();
            in.close();
            socket.close();
            
            if (outputLine == null || !outputLine.startsWith("ERROR") || !outputLine.startsWith("CORRUPTED")) {
                setStatus("Completed");
                setDataExchange("");
            }
            
        } catch (Exception e) {
            setStatus("ERROR: " + e.getMessage());
        }

        //removeConnection();


    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void addConnection() {
        synchronized (model) {
            row = model.getRowCount();
            model.insertRow(row, new Object[]{row + 1, socket.getInetAddress().getHostName(), socket.getPort(), getDateTime()});
        }
    }

    private void setStatus(String message) {
        synchronized (model) {
            for (int i = 0; i < model.getRowCount(); ++i) {
                if (((Integer) model.getValueAt(i, 0)) == row + 1) {
                    model.setValueAt(message, i, 6);
                    break;
                }
            }
        }
    }

    private void setConnectionType(String message) {
        synchronized (model) {
            for (int i = 0; i < model.getRowCount(); ++i) {
                if (((Integer) model.getValueAt(i, 0)) == row + 1) {
                    model.setValueAt(message, i, 4);
                    break;
                }
            }
        }
    }

    private void setDataExchange(String message) {
        synchronized (model) {
            for (int i = 0; i < model.getRowCount(); ++i) {
                if (((Integer) model.getValueAt(i, 0)) == row + 1) {
                    model.setValueAt(message, i, 5);
                    break;
                }
            }
        }
    }
}
