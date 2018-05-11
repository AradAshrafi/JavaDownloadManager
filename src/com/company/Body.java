package com.company;

import javax.swing.*;
import java.awt.*;

public class Body extends JPanel {
    public Body() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        DownloadsPanel downloadsPanel = new DownloadsPanel();
        add(downloadsPanel);
        setVisible(true);
    }
}
