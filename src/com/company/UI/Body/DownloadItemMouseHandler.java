package com.company.UI.Body;

import com.company.BetweenClassesRelation.DownloadItemsConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

public class DownloadItemMouseHandler implements MouseListener {
    private HashSet<DownloadItem> selectedItems;


    public DownloadItemMouseHandler(DownloadItemsConnection downloadItemsConnection) {
        this.selectedItems = downloadItemsConnection.getSelectedItems();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {

        }
        if (e.getClickCount() == 2) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof DownloadItem) {
            DownloadItem clickedItem = (DownloadItem) (e.getSource());
            if (selectedItems.contains(clickedItem)) {
                selectedItems.remove(clickedItem);
                System.out.println("removed");
                clickedItem.setBackground(null);
            } else {
                selectedItems.add(clickedItem);
                System.out.println("Added");
                clickedItem.setBackground(Color.red);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
