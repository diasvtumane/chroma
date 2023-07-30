package com.example.chroma;

import javax.swing.SwingUtilities;

public class AppStoreMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppStoreGUI appStore = new AppStoreGUI();
            appStore.setVisible(true); // Make the frame visible after initialization
        });
    }
}
