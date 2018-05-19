package com.company.UI.Body;

import com.company.UI.UI;

import javax.swing.*;
import java.awt.*;


public class Body extends JPanel {
    private DownloadsPanel downloadsPanel;
    private JPanel contentPane;
    private JScrollPane downloadsPanelScrollbar;

    public Body(UI ui) {
        setLayout(new GridLayout(0, 1));

        contentPane = new JPanel(new GridLayout(0, 1));
        /**
         * passing ui to DownloadPanel constructor to cast it in future
         */
        downloadsPanel = new DownloadsPanel(ui);
        downloadsPanelScrollbar = new JScrollPane(downloadsPanel);
        downloadsPanelScrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        downloadsPanelScrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        /**
         * adding whole components to scrollPane
         */
        contentPane.add(downloadsPanelScrollbar);
        /**
         * adding scrollPane to UI
         */
        add(contentPane);
        setVisible(true);
    }
    public void setDownloadsPanel(JPanel newPanel){}

    public DownloadsPanel getDownloadsPanel() {
        return downloadsPanel;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JScrollPane getDownloadsPanelScrollbar() {
        return downloadsPanelScrollbar;
    }
}
