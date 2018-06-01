package com.company.UI.Body;

import com.company.UI.BetweenClassesRelation.StaticData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFileChooser extends JFrame {
    private JFileChooser fileChooser;

    public MyFileChooser() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File(StaticData.getLocation()));
        fileChooser.setDialogTitle("Choose Direction Of Saved Files");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        System.out.println(StaticData.getLocation());
        //
        // disable the "All files" option.
        //
        fileChooser.setAcceptAllFileFilterUsed(false);
        //
        /**
         * saving new selected path to save files
         */
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            StaticData.SetLocation(fileChooser.getSelectedFile().toString());
            fileChooser.cancelSelection();

        } else {
            System.out.println("No Selection ");

        }
        add(fileChooser);
        pack();
        setVisible(true);
    }
}
