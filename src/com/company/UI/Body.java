package com.company.UI;

import javax.swing.*;
import java.awt.*;

public class Body extends JPanel {
    private DownloadsPanel downloadsPanel;

    public Body(UI ui) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        /**
         * passing ui to DownloadPanel constructor to cast it in future
         */
        downloadsPanel = new DownloadsPanel(ui);
        add(downloadsPanel);
        setVisible(true);
    }

    public DownloadsPanel getDownloadsPanel() {
        return downloadsPanel;
    }
}
