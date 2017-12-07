
package radiomapserver;

import java.awt.Toolkit;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * Server main form. Gives the opportunity to Start/Stop indoor or outdoor mode,
 * create radio map files and parameters and monitor connections with clients.
 */
public class RadioMapServer extends javax.swing.JFrame {

    // Table for indoor connections
    private final DefaultTableModel model_Server_Indoor = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    // Table for outdoor connections
    private final DefaultTableModel model_Server_Outdoor = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private RadioMap rmIndoor;
    private RadioMap rmOutdoor;
    private final Settings set = new Settings();
    private ServerMode IndoorMode;
    private ServerMode OutdoorMode;
    private int indoorPort;
    private int outdoorPort;
    private final String indoorFolder = "indoor";
    private final String outdoorFolder = "outdoor";
    private final String indoorRSSFolder = "indoor-rss-logs";
    private final String outdoorRSSFolder = "outdoor-rss-logs";
    private final String indoorFilename = indoorFolder + "/indoor-radiomap.txt";
    private final String indoorTestData = indoorFolder + "/test-data.txt";
    private final String outdoorTestData = outdoorFolder + "/test-data.txt";
    private final String outdoorFilename = outdoorFolder + "/outdoor-radiomap.txt";

    /** Creates new form LoginArea */
    public RadioMapServer() {
        initComponents();
        createServerFolders();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        Server_Panel = new javax.swing.JPanel();
        Server_Tabbed_Pane = new javax.swing.JTabbedPane();
        Indoor_Mode = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        Indoor_Connections_Table = new javax.swing.JTable(this.model_Server_Indoor);
        Clear_Indoor_Button = new javax.swing.JButton();
        Create_Indoor_Radiomap_Button = new javax.swing.JButton();
        Create_Indoor_Radiomap_Parameters = new javax.swing.JButton();
        Outdoor_Mode = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        Outdoor_Connections_Table = new javax.swing.JTable(this.model_Server_Outdoor);
        Clear_Outdoor_Button = new javax.swing.JButton();
        Create_Outdoor_Radiomap_Button = new javax.swing.JButton();
        Create_Outdoor_Radiomap_Parameters = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        State_Indoor_Text = new javax.swing.JTextField();
        Start_Stop_IndoorToggleButton = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logIndoorDisplay = new javax.swing.JTextArea();
        Start_Stop_ToggleButton = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        logOutdoorDisplay = new javax.swing.JTextArea();
        Start_Stop_OutdoorToggleButton = new javax.swing.JToggleButton();
        State_Outdoor_Text = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        Close = new javax.swing.JMenuItem();
        Edit = new javax.swing.JMenu();
        Settings = new javax.swing.JMenuItem();
        Help = new javax.swing.JMenu();
        About = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Radiomap Server");
        setExtendedState(this.MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().createImage("./src/SF_icon.png"));
        setLocationByPlatform(true);
        setName(""); // NOI18N

        Background.setBackground(new java.awt.Color(153, 204, 255));
        Background.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Server_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Server Pending Connections"));

        Server_Tabbed_Pane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                Server_Tabbed_PaneStateChanged(evt);
            }
        });

        model_Server_Indoor.addColumn("A/A");
        model_Server_Indoor.addColumn("Client Host Name");
        model_Server_Indoor.addColumn("Port");
        model_Server_Indoor.addColumn("Time Connected");
        model_Server_Indoor.addColumn("Type");
        model_Server_Indoor.addColumn("Data Exchange");
        model_Server_Indoor.addColumn("Status");
        Indoor_Connections_Table.setBackground(new java.awt.Color(153, 204, 255));
        Indoor_Connections_Table.setModel(this.model_Server_Indoor);
        Indoor_Connections_Table.setRowSelectionAllowed(true);
        jScrollPane17.setViewportView(Indoor_Connections_Table);
        Indoor_Connections_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Clear_Indoor_Button.setText("Clear Finished");
        Clear_Indoor_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear_Indoor_ButtonActionPerformed(evt);
            }
        });

        Create_Indoor_Radiomap_Button.setText("Create Indoor Radiomap");
        Create_Indoor_Radiomap_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_Indoor_Radiomap_ButtonActionPerformed(evt);
            }
        });

        Create_Indoor_Radiomap_Parameters.setText("Create Indoor Parameters");
        Create_Indoor_Radiomap_Parameters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_Indoor_Radiomap_ParametersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Indoor_ModeLayout = new javax.swing.GroupLayout(Indoor_Mode);
        Indoor_Mode.setLayout(Indoor_ModeLayout);
        Indoor_ModeLayout.setHorizontalGroup(
            Indoor_ModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Indoor_ModeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Indoor_ModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 1235, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Indoor_ModeLayout.createSequentialGroup()
                        .addComponent(Create_Indoor_Radiomap_Button)
                        .addGap(18, 18, 18)
                        .addComponent(Create_Indoor_Radiomap_Parameters)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 711, Short.MAX_VALUE)
                        .addComponent(Clear_Indoor_Button)))
                .addContainerGap())
        );
        Indoor_ModeLayout.setVerticalGroup(
            Indoor_ModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Indoor_ModeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(Indoor_ModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Clear_Indoor_Button)
                    .addComponent(Create_Indoor_Radiomap_Button)
                    .addComponent(Create_Indoor_Radiomap_Parameters))
                .addContainerGap())
        );

        Server_Tabbed_Pane.addTab("Indoor Mode", Indoor_Mode);

        Outdoor_Connections_Table.setModel(this.model_Server_Outdoor);
        model_Server_Outdoor.addColumn("A/A");
        model_Server_Outdoor.addColumn("Client Host Name");
        model_Server_Outdoor.addColumn("Port");
        model_Server_Outdoor.addColumn("Time Connected");
        model_Server_Outdoor.addColumn("Type");
        model_Server_Outdoor.addColumn("Data Exchange");
        model_Server_Outdoor.addColumn("Status");
        jScrollPane18.setViewportView(Outdoor_Connections_Table);
        Outdoor_Connections_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Clear_Outdoor_Button.setText("Clear Finished");
        Clear_Outdoor_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear_Outdoor_ButtonActionPerformed(evt);
            }
        });

        Create_Outdoor_Radiomap_Button.setText("Create Outdoor Radiomap");
        Create_Outdoor_Radiomap_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_Outdoor_Radiomap_ButtonActionPerformed(evt);
            }
        });

        Create_Outdoor_Radiomap_Parameters.setText("Create Outdoor Parameters");
        Create_Outdoor_Radiomap_Parameters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_Outdoor_Radiomap_ParametersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Outdoor_ModeLayout = new javax.swing.GroupLayout(Outdoor_Mode);
        Outdoor_Mode.setLayout(Outdoor_ModeLayout);
        Outdoor_ModeLayout.setHorizontalGroup(
            Outdoor_ModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Outdoor_ModeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Outdoor_ModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 1235, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Outdoor_ModeLayout.createSequentialGroup()
                        .addComponent(Create_Outdoor_Radiomap_Button)
                        .addGap(18, 18, 18)
                        .addComponent(Create_Outdoor_Radiomap_Parameters)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 689, Short.MAX_VALUE)
                        .addComponent(Clear_Outdoor_Button)))
                .addContainerGap())
        );
        Outdoor_ModeLayout.setVerticalGroup(
            Outdoor_ModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Outdoor_ModeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(Outdoor_ModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Clear_Outdoor_Button)
                    .addGroup(Outdoor_ModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Create_Outdoor_Radiomap_Button)
                        .addComponent(Create_Outdoor_Radiomap_Parameters)))
                .addContainerGap())
        );

        Server_Tabbed_Pane.addTab("Outdoor Mode", Outdoor_Mode);

        javax.swing.GroupLayout Server_PanelLayout = new javax.swing.GroupLayout(Server_Panel);
        Server_Panel.setLayout(Server_PanelLayout);
        Server_PanelLayout.setHorizontalGroup(
            Server_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Server_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Server_Tabbed_Pane)
                .addContainerGap())
        );
        Server_PanelLayout.setVerticalGroup(
            Server_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Server_PanelLayout.createSequentialGroup()
                .addComponent(Server_Tabbed_Pane)
                .addContainerGap())
        );

        jLabel1.setText("State:");

        State_Indoor_Text.setText("Not running");
        State_Indoor_Text.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        State_Indoor_Text.setEnabled(false);

        Start_Stop_IndoorToggleButton.setText("Start Running Indoor Module");
        Start_Stop_IndoorToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Start_Stop_IndoorToggleButtonActionPerformed(evt);
            }
        });

        logIndoorDisplay.setColumns(20);
        logIndoorDisplay.setRows(5);
        logIndoorDisplay.setDisabledTextColor(new java.awt.Color(0, 0, 204));
        logIndoorDisplay.setEnabled(false);
        jScrollPane1.setViewportView(logIndoorDisplay);

        Start_Stop_ToggleButton.setText("Start Running");
        Start_Stop_ToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Start_Stop_ToggleButtonActionPerformed(evt);
            }
        });

        logOutdoorDisplay.setColumns(20);
        logOutdoorDisplay.setRows(5);
        logOutdoorDisplay.setDisabledTextColor(new java.awt.Color(0, 0, 204));
        logOutdoorDisplay.setEnabled(false);
        jScrollPane2.setViewportView(logOutdoorDisplay);

        Start_Stop_OutdoorToggleButton.setText("Start Running Outdoor Module");
        Start_Stop_OutdoorToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Start_Stop_OutdoorToggleButtonActionPerformed(evt);
            }
        });

        State_Outdoor_Text.setText("Not running");
        State_Outdoor_Text.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        State_Outdoor_Text.setEnabled(false);

        jLabel2.setText("State:");

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(Start_Stop_ToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addComponent(Start_Stop_IndoorToggleButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(10, 10, 10)
                                .addComponent(State_Indoor_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addComponent(Start_Stop_OutdoorToggleButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(State_Outdoor_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Server_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        BackgroundLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        BackgroundLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {State_Indoor_Text, State_Outdoor_Text});

        BackgroundLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Start_Stop_IndoorToggleButton, Start_Stop_OutdoorToggleButton});

        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(Start_Stop_ToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(BackgroundLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(State_Indoor_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(Start_Stop_IndoorToggleButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                        .addComponent(State_Outdoor_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addComponent(Start_Stop_OutdoorToggleButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
                        .addGap(3, 3, 3)))
                .addComponent(Server_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        BackgroundLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {State_Indoor_Text, State_Outdoor_Text});

        BackgroundLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Start_Stop_IndoorToggleButton, Start_Stop_OutdoorToggleButton});

        File.setText("File");

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });
        File.add(Close);

        MenuBar.add(File);

        Edit.setText("Edit");

        Settings.setText("Settings");
        Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
            }
        });
        Edit.add(Settings);

        MenuBar.add(Edit);

        Help.setText("Help");

        About.setText("About");
        About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });
        Help.add(About);

        MenuBar.add(Help);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-1328)/2, (screenSize.height-709)/2, 1328, 709);
    }// </editor-fold>//GEN-END:initComponents

    //Print an error msg
    private void displayErrorMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    //Close application
    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        System.exit(1);
    }//GEN-LAST:event_CloseActionPerformed

    //Show Settings form
    private void SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsActionPerformed

        set.setLocationRelativeTo(this);
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                set.setVisible(true);
                set.readSavedOptions();
            }
        });
    }//GEN-LAST:event_SettingsActionPerformed

    // Show About form
    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
        final About about = new About();
        about.setLocationRelativeTo(this);
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                about.setVisible(true);
            }
        });
    }//GEN-LAST:event_AboutActionPerformed

    private void Server_Tabbed_PaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_Server_Tabbed_PaneStateChanged
        if (Server_Tabbed_Pane.getSelectedIndex() == 0) {
        } else if (Server_Tabbed_Pane.getSelectedIndex() == 1) {
        }
}//GEN-LAST:event_Server_Tabbed_PaneStateChanged

    private void Start_Stop_IndoorToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Start_Stop_IndoorToggleButtonActionPerformed
        startIndoorServer();
    }//GEN-LAST:event_Start_Stop_IndoorToggleButtonActionPerformed

    private void Start_Stop_ToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Start_Stop_ToggleButtonActionPerformed
        if (Start_Stop_ToggleButton.isSelected()) {
            Start_Stop_ToggleButton.setText("Stop Running");
            Start_Stop_IndoorToggleButton.setSelected(true);
            Start_Stop_OutdoorToggleButton.setSelected(true);
        } else {
            Start_Stop_ToggleButton.setText("Start Running");
            Start_Stop_IndoorToggleButton.setSelected(false);
            Start_Stop_OutdoorToggleButton.setSelected(false);
        }
        startIndoorServer();
        startOutdoorServer();
    }//GEN-LAST:event_Start_Stop_ToggleButtonActionPerformed

    private void Start_Stop_OutdoorToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Start_Stop_OutdoorToggleButtonActionPerformed
        startOutdoorServer();
    }//GEN-LAST:event_Start_Stop_OutdoorToggleButtonActionPerformed

    private void Create_Outdoor_Radiomap_ParametersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_Outdoor_Radiomap_ParametersActionPerformed
        File folderOutdoor = new File(outdoorRSSFolder);
        rmOutdoor = new RadioMap(folderOutdoor, outdoorFilename, set.getDefaultNaNValue());

        if (!rmOutdoor.writeParameters(outdoorTestData)) {
            JOptionPane.showMessageDialog(null, "There was a problem creating outdoor parameters.\n"
                    + "Existed outdoor parameters will be used if exist!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Created new outdoor parameters!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_Create_Outdoor_Radiomap_ParametersActionPerformed

    private void Create_Indoor_Radiomap_ParametersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_Indoor_Radiomap_ParametersActionPerformed
        File folderIndoor = new File(indoorRSSFolder);
        rmIndoor = new RadioMap(folderIndoor, indoorFilename, set.getDefaultNaNValue());

        if (!rmIndoor.writeParameters(indoorTestData)) {
            JOptionPane.showMessageDialog(null, "There was a problem creating indoor parameters.\n"
                    + "Existed indoor parameters will be used if exist!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Created new indoor parameters!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Create_Indoor_Radiomap_ParametersActionPerformed

    private void Create_Indoor_Radiomap_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_Indoor_Radiomap_ButtonActionPerformed
        File folderIndoor = new File(indoorRSSFolder);
        if (folderIndoor.exists() && folderIndoor.canRead() && folderIndoor.isDirectory()) {
            rmIndoor = new RadioMap(folderIndoor, indoorFilename, set.getDefaultNaNValue());
            if (!rmIndoor.createRadioMap()) {
                JOptionPane.showMessageDialog(null, "There was a problem creating the indoor radio map.\n"
                        + "Existed Indoor Radio Map will be used if exists!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Created new indoor Radio Map!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, indoorRSSFolder + " folder does not exist. Restart Server", "Warning", JOptionPane.WARNING_MESSAGE);
        }
}//GEN-LAST:event_Create_Indoor_Radiomap_ButtonActionPerformed

    private void Create_Outdoor_Radiomap_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_Outdoor_Radiomap_ButtonActionPerformed
        File folderOutdoor = new File(outdoorRSSFolder);
        if (folderOutdoor.exists() && folderOutdoor.canRead() && folderOutdoor.isDirectory()) {
            rmOutdoor = new RadioMap(folderOutdoor, outdoorFilename, set.getDefaultNaNValue());
            if (!rmOutdoor.createRadioMap()) {
                JOptionPane.showMessageDialog(null, "There was a problem creating the outdoor radio map.\n"
                        + "Existed Outdoor Radio Map will be used if exists!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Created new outdoor Radio Map!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, outdoorRSSFolder + " folder does not exist. Restart Server", "Warning", JOptionPane.WARNING_MESSAGE);
        }
}//GEN-LAST:event_Create_Outdoor_Radiomap_ButtonActionPerformed

    private void Clear_Indoor_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Clear_Indoor_ButtonActionPerformed
        synchronized (model_Server_Indoor) {
            for (int i = 0; i < model_Server_Indoor.getRowCount(); ++i) {
                if (!((String) model_Server_Indoor.getValueAt(i, 6)).equalsIgnoreCase("Pending")) {
                    model_Server_Indoor.removeRow(i--);
                }
            }
        }
    }//GEN-LAST:event_Clear_Indoor_ButtonActionPerformed

    private void Clear_Outdoor_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Clear_Outdoor_ButtonActionPerformed
        synchronized (model_Server_Outdoor) {
            for (int i = 0; i < model_Server_Outdoor.getRowCount(); ++i) {
                if (!((String) model_Server_Outdoor.getValueAt(i, 6)).equalsIgnoreCase("Pending")) {
                    model_Server_Outdoor.removeRow(i--);
                }
            }
        }
    }//GEN-LAST:event_Clear_Outdoor_ButtonActionPerformed

    private void startIndoorServer() {
        if (Start_Stop_IndoorToggleButton.isSelected()) {
            Start_Stop_IndoorToggleButton.setText("Stop Running Indoor Module");

            if (IndoorMode == null) {
                indoorPort = set.getPortNumIndoor();
                IndoorMode = new ServerMode(indoorPort, indoorRSSFolder, new File(indoorFilename.replace(".", "-mean.")), ServerMode.MODE.INDOOR, logIndoorDisplay, State_Indoor_Text, model_Server_Indoor);
                IndoorMode.start();
            }

            if (Start_Stop_OutdoorToggleButton.isSelected()) {
                Start_Stop_ToggleButton.setSelected(true);
                Start_Stop_ToggleButton.setText("Stop Running");
            }

        } else {
            Start_Stop_IndoorToggleButton.setText("Start Running Indoor Module");

            if (IndoorMode != null && IndoorMode.isRunning()) {

                IndoorMode.stopRunning();
                IndoorMode = null;

            } else {
                IndoorMode = null;
            }

            if (!Start_Stop_OutdoorToggleButton.isSelected()) {
                Start_Stop_ToggleButton.setSelected(false);
                Start_Stop_ToggleButton.setText("Start Running");
            }
        }
    }

    private void startOutdoorServer() {
        if (Start_Stop_OutdoorToggleButton.isSelected()) {
            Start_Stop_OutdoorToggleButton.setText("Stop Running Outdoor Module");

            if (OutdoorMode == null) {
                outdoorPort = set.getPortNumOutdoor();
                OutdoorMode = new ServerMode(outdoorPort, outdoorRSSFolder, new File(outdoorFilename.replace(".", "-mean.")), ServerMode.MODE.OUTDOOR, logOutdoorDisplay, State_Outdoor_Text, model_Server_Outdoor);
                OutdoorMode.start();
            }

            if (Start_Stop_IndoorToggleButton.isSelected()) {
                Start_Stop_ToggleButton.setSelected(true);
                Start_Stop_ToggleButton.setText("Stop Running");
            }

        } else {
            Start_Stop_OutdoorToggleButton.setText("Start Running Outdoor Module");

            if (OutdoorMode != null && OutdoorMode.isRunning()) {

                OutdoorMode.stopRunning();
                OutdoorMode = null;
            } else {
                OutdoorMode = null;
            }

            if (!Start_Stop_IndoorToggleButton.isSelected()) {
                Start_Stop_ToggleButton.setSelected(false);
                Start_Stop_ToggleButton.setText("Start Running");
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem About;
    private javax.swing.JPanel Background;
    private javax.swing.JButton Clear_Indoor_Button;
    private javax.swing.JButton Clear_Outdoor_Button;
    private javax.swing.JMenuItem Close;
    private javax.swing.JButton Create_Indoor_Radiomap_Button;
    private javax.swing.JButton Create_Indoor_Radiomap_Parameters;
    private javax.swing.JButton Create_Outdoor_Radiomap_Button;
    private javax.swing.JButton Create_Outdoor_Radiomap_Parameters;
    private javax.swing.JMenu Edit;
    private javax.swing.JMenu File;
    private javax.swing.JMenu Help;
    private javax.swing.JTable Indoor_Connections_Table;
    private javax.swing.JPanel Indoor_Mode;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JTable Outdoor_Connections_Table;
    private javax.swing.JPanel Outdoor_Mode;
    private javax.swing.JPanel Server_Panel;
    private javax.swing.JTabbedPane Server_Tabbed_Pane;
    private javax.swing.JMenuItem Settings;
    private javax.swing.JToggleButton Start_Stop_IndoorToggleButton;
    private javax.swing.JToggleButton Start_Stop_OutdoorToggleButton;
    private javax.swing.JToggleButton Start_Stop_ToggleButton;
    private javax.swing.JTextField State_Indoor_Text;
    private javax.swing.JTextField State_Outdoor_Text;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea logIndoorDisplay;
    private javax.swing.JTextArea logOutdoorDisplay;
    // End of variables declaration//GEN-END:variables

    private void createServerFolders() {
        File f = null;
        boolean success = false;

        // Create directory for indoor radiomap, radiomap mean, parameters, rbf-weights
        f = new File(indoorFolder);
        if (!f.exists() || !f.isDirectory()) {
            success = f.mkdir();
            if (!success) {
                displayErrorMsg("Could not create indoor folder!\nChange directory of server");
                System.exit(1);
            }
        }

        // Create directory for outdoor radiomap, radiomap mean, parameters, rbf-weights
        f = new File(outdoorFolder);
        if (!f.exists() || !f.isDirectory()) {
            success = f.mkdir();
            if (!success) {
                displayErrorMsg("Could not create outdoor folder!\nChange directory of server");
                System.exit(1);
            }
        }

        // Create directory for indoor RSS files
        f = new File(indoorRSSFolder);
        if (!f.exists() || !f.isDirectory()) {
            success = f.mkdir();
            if (!success) {
                displayErrorMsg("Could not create indoor folder for RSS log files!\nChange directory of server");
                System.exit(1);
            }
        }

        // Create directory for outdoor RSS files
        f = new File(outdoorRSSFolder);
        if (!f.exists() || !f.isDirectory()) {
            success = f.mkdir();
            if (!success) {
                displayErrorMsg("Could not create outdoor folder for RSS log files!\nChange directory of server");
                System.exit(1);
            }
        }
    }
}
