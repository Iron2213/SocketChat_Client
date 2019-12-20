package com.company;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Andrea Bacci
 * @author andrea00bacci@libero.it
 */
public class ProgettoChatSocket {

    public static void main(String[] args) {
        // Creazione oggetto per la finestra principale
        JFrame frame = new JFrame("Chat");
        
        // Creazione e avvio del client
        ControlClass panel = new ControlClass();
        panel.startUI();
        
        // Imposta nome della finestra
        frame.setTitle("Chat - " + panel.getUserName());
        
        /*
        Finestra principale
        
        Impostazione dimensione preferita
        Disattivazione possibilità di scrivere e modificare
        Impostazione layout della finestra
        Aggiunta del pannello centrale
        Impostazione operazione default di chiusura
        Attuazione dimensioni all'interno della finestra
        Impostazione della finestra come visibile
        */
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(panel.getPanelClass(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}