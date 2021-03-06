package mediathek.gui.abo;

import mediathek.config.Daten;
import mediathek.config.MVConfig;
import mediathek.tool.EscapeKeyHandler;
import mediathek.tool.GuiFunktionen;

import javax.swing.*;
import java.awt.*;

public class ManageAboDialog extends JDialog {
    private final ManageAboPanel aboPanel;

    public ManageAboDialog(Frame owner, Daten daten) {
        super(owner);
        setTitle("Abos verwalten");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);

        aboPanel = new ManageAboPanel();
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(aboPanel, BorderLayout.CENTER);

        pack();
        //restore saved size
        GuiFunktionen.setSize(MVConfig.Configs.SYSTEM_GROESSE_MANAGE_ABO, this, owner);

        EscapeKeyHandler.installHandler(this, this::dispose);
    }

    public ManageAboPanel getAboPanel() {
        return aboPanel;
    }
}
