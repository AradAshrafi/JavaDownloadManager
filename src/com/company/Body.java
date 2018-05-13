package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class Body extends JPanel {
    public Body(UI ui) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        /**
         * passing ui to DownloadPanel constructor to cast it in future
         */
        DownloadsPanel downloadsPanel = new DownloadsPanel(ui);
        add(downloadsPanel);
        setVisible(true);
    }
}
