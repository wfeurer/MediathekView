package mediathek.gui.dialogEinstellungen;

import mediathek.config.Daten;
import mediathek.config.Icons;
import mediathek.config.MVConfig;
import mediathek.gui.PanelVorlage;
import mediathek.gui.dialog.DialogHilfe;
import mediathek.gui.messages.ParallelDownloadNumberChangedEvent;
import mediathek.tool.ApplicationConfiguration;
import net.engio.mbassy.listener.Handler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

@SuppressWarnings("serial")
public class PanelDownload extends PanelVorlage {

    @Handler
    private void handleParallelDownloadNumberChange(ParallelDownloadNumberChangedEvent e) {
        SwingUtilities.invokeLater(() -> jSpinnerAnzahlDownload.setValue(Integer.parseInt(MVConfig.get(MVConfig.Configs.SYSTEM_MAX_DOWNLOAD))));
    }

    public PanelDownload(Daten d, JFrame parent) {
        super(d, parent);
        initComponents();
        daten = d;

        daten.getMessageBus().subscribe(this);

        jSpinnerAnzahlDownload.setModel(new javax.swing.SpinnerNumberModel(1, 1, 9, 1));
        jSpinnerAnzahlDownload.setValue(Integer.parseInt(MVConfig.get(MVConfig.Configs.SYSTEM_MAX_DOWNLOAD)));
        jSpinnerAnzahlDownload.addChangeListener(new BeobSpinnerDownload());
        jButtonHilfeAnzahl.setIcon(Icons.ICON_BUTTON_HELP);
        jButtonHilfeAnzahl.addActionListener(e -> new DialogHilfe(parentComponent, true, "\n"
                + "Hier kann angegeben werden, wie viele\n"
                + "Downloads gleichzeitig gestartet werden können.\n\n"
                + "Es gibt jedoch noch eine Begrenzung\n"
                + "die trotzdem nicht überschritten wird:\n\n"
                + "2 Downloads pro Server, das kann auch noch\n"
                + "auf 1 Download pro Server\n"
                + "(z.B. nur ein Download von \"www.zdf.de\")\n"
                + "weiter begrenzt werden.").setVisible(true));

        cbkDownloadError.setSelected(Boolean.parseBoolean(MVConfig.get(MVConfig.Configs.SYSTEM_DOWNLOAD_ERRORMSG)));
        cbkDownloadError.addActionListener(e -> MVConfig.add(MVConfig.Configs.SYSTEM_DOWNLOAD_ERRORMSG, Boolean.toString(cbkDownloadError.isSelected())));

        jCheckBoxBeep.setSelected(ApplicationConfiguration.getConfiguration().getBoolean(ApplicationConfiguration.DOWNLOAD_SOUND_BEEP,false));
        jCheckBoxBeep.addActionListener(l -> ApplicationConfiguration.getConfiguration().setProperty(ApplicationConfiguration.DOWNLOAD_SOUND_BEEP,jCheckBoxBeep.isSelected()));

        jButtonBeep.addActionListener(ae -> Toolkit.getDefaultToolkit().beep());

        jCheckBoxServer.setSelected(Boolean.parseBoolean(MVConfig.get(MVConfig.Configs.SYSTEM_MAX_1_DOWNLOAD_PRO_SERVER)));
        jCheckBoxServer.addActionListener(ae -> MVConfig.add(MVConfig.Configs.SYSTEM_MAX_1_DOWNLOAD_PRO_SERVER, String.valueOf(jCheckBoxServer.isSelected())));
    }

    private class BeobSpinnerDownload implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent arg0) {
            MVConfig.add(MVConfig.Configs.SYSTEM_MAX_DOWNLOAD,
                    String.valueOf(((Number) jSpinnerAnzahlDownload.getModel().getValue()).intValue()));

            daten.getMessageBus().publishAsync(new ParallelDownloadNumberChangedEvent());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        jSpinnerAnzahlDownload = new javax.swing.JSpinner();
        jButtonHilfeAnzahl = new javax.swing.JButton();
        jCheckBoxBeep = new javax.swing.JCheckBox();
        jButtonBeep = new javax.swing.JButton();
        jCheckBoxServer = new javax.swing.JCheckBox();
        cbkDownloadError = new javax.swing.JCheckBox();

        setMinimumSize(getPreferredSize());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setText("gleichzeitige Downloads laden:");

        jSpinnerAnzahlDownload.setModel(new javax.swing.SpinnerNumberModel(1, 1, 9, 1));

        jButtonHilfeAnzahl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/muster/button-help.png"))); // NOI18N
        jButtonHilfeAnzahl.setToolTipText("Hilfe anzeigen");

        jCheckBoxBeep.setText("nach jedem Download einen \"Beep\" ausgeben");

        jButtonBeep.setText("Testen");

        jCheckBoxServer.setText("nur ein Download pro Downloadserver");

        cbkDownloadError.setText("Bei Downloadfehler, Fehlermeldung anzeigen");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jCheckBoxServer))
                    .addComponent(cbkDownloadError)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBoxBeep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonBeep))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(51, 51, 51)
                        .addComponent(jSpinnerAnzahlDownload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonHilfeAnzahl)))
                .addGap(0, 131, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbkDownloadError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxBeep)
                    .addComponent(jButtonBeep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jSpinnerAnzahlDownload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonHilfeAnzahl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxServer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(456, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbkDownloadError;
    private javax.swing.JButton jButtonBeep;
    private javax.swing.JButton jButtonHilfeAnzahl;
    private javax.swing.JCheckBox jCheckBoxBeep;
    private javax.swing.JCheckBox jCheckBoxServer;
    private javax.swing.JSpinner jSpinnerAnzahlDownload;
    // End of variables declaration//GEN-END:variables
}
