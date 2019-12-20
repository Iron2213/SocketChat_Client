package com.company;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Andrea Bacci
 * @author andrea00bacci@libero.it
 */
public class MainPanel extends JPanel {
    private final GroupLayout gL;
    private final JSeparator sep;
    private final JTextArea txtA;
    private final JTextArea userTxtArea;
    private final JScrollPane scroll;
    public final JButton btn1;
    
    public MainPanel() {
        gL = new GroupLayout(this);
        btn1 = new JButton("Invio");
        userTxtArea = new JTextArea();
        txtA = new JTextArea();
        sep = new JSeparator();
        scroll = new JScrollPane(txtA, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    // Metodo per l'avvio dell'interfaccia
    public void startPanel() {
        // Impostazione layout utilizzato
        setLayout(gL);
        
        // Impostazione colore di sfondo
        setBackground(new Color(42, 42, 46));
        
        /*
        Separatore tra area di txtA e userTxtArea
        
        Impostazione colori di sfondo e bordo
        */
        sep.setBackground(new Color(77, 0, 77));
        sep.setBorder(BorderFactory.createLineBorder(new Color(77, 0, 77), 1));
        
        /*
        Area di visualizzazione dei messaggi di tutti gli utenti
        
        Impostazione colore sfondo e font del testo
        Attivazione a capo automatico
        Disattivazione possibilità di scrivere e modificare
        */
        txtA.setBackground(new Color(70, 68, 89));
        txtA.setForeground(Color.white);
        txtA.setLineWrap(true);
        txtA.setWrapStyleWord(true);
        txtA.setEditable(false);
        
        /*
        Area di scrittura dei messaggi da parte dell'utente
        
        Impostazione dimensione preferita, colore di 
        sfondo, di font e bordo
        Attivazione a capo automatico
        */
        userTxtArea.setPreferredSize(new Dimension(400, 50));
        userTxtArea.setBackground(new Color(70, 68, 89));
        userTxtArea.setForeground(Color.white);
        userTxtArea.setLineWrap(true);
        userTxtArea.setBorder(BorderFactory
                .createLineBorder(new Color(77, 0, 77), 1));
        
        /*
        Bottone per l'invio del messaggio
        
        Impostazione dimensione minima per evitare che il bottone assuma 
        dimensioni sbagliate
        Impostazione colore di sfondo, di font e bordo
        Disattivazione focus su testo del bottone
        */
        btn1.setMinimumSize(new Dimension(50, 50));
        btn1.setBackground(new Color(70, 68, 89));
        btn1.setBorder(BorderFactory
                .createLineBorder(new Color(77, 0, 77), 1));
        btn1.setForeground(Color.white);
        btn1.setFocusPainted(false);
        
        /*
        Panello per permettere all'area di testo principale di scorrere per 
        visualizzare messaggi addizionali
        
        Impostazione colore del bordo e sfondo della barra di scorrimento
        Impostazione dimensione preferita
        */
        scroll.setBorder(BorderFactory
                .createLineBorder(new Color(77, 0, 77), 1));
        scroll.getVerticalScrollBar().setBackground(new Color(70, 68, 89));
        scroll.setPreferredSize(new Dimension(400, 200));
        
        initializeLayout();
    }
    
    private void initializeLayout() {
        // Attivazione gap automatici tra i componenti e bordi della finestra
        gL.setAutoCreateGaps(true);
        gL.setAutoCreateContainerGaps(true);

        gL.setHorizontalGroup(gL.createSequentialGroup()
                .addGroup(gL.createParallelGroup()
                    .addComponent(scroll)
                    .addComponent(sep)
                    .addGroup(gL.createSequentialGroup()
                        .addComponent(userTxtArea)
                        .addComponent(btn1)))
        );

        gL.setVerticalGroup(gL.createSequentialGroup()
                .addComponent(scroll)
                .addGroup(gL.createSequentialGroup()
                    .addComponent(sep, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(gL.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(userTxtArea, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn1))
        );
    }
    
    /**
     *
     * @param user Username del client
     */
    public void addText(String user) {
        addText(user, userTxtArea.getText());
        userTxtArea.setText("");
    }
    
    /**
     *
     * @param user Username del client
     * @param text Messaggio ricevuto dal server
     */
    public void addText(String user, String text) {
        txtA.append("< " + user +" > : " + text + "\n");
        txtA.setCaretPosition(txtA.getDocument().getLength());
    }
    
    /**
     *
     * @return Ritorna l'oggetto dell'area di testo dove scrive il client
     */
    public String getText() {
        return userTxtArea.getText();
    }
}
