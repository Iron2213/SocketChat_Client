package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Andrea Bacci
 * @author andrea00bacci@libero.it
 */
public class ControlClass {
    private MainPanel mP = null;
    private DataInputStream inStream = null;
    private DataOutputStream outStream = null;
    private Thread listeningThread;
    private String user;
    
    public void startUI() {
        String IP;
        IP = askPopUp("Indirizzo", "Inserire indirizzo IP del server a cui connettersi");
        
        try {
            /* 
            Creazione socket per il client definendo indirizzo IP e porta
            a cui connettersi
            */
            Socket IO = new Socket(IP, 6789);
            
            // Creazione canali di comunicazione input e output
            inStream = new DataInputStream(IO.getInputStream());
            outStream = new DataOutputStream(IO.getOutputStream());
        }
        catch (IOException ex) {
            System.out.println(ex);
            System.exit(-1);
        }
        
        // Avvio interfaccia utente
        mP = new MainPanel();
        mP.startPanel();
        
        // Creazione actionListener per bottone di invio del messaggio
        mP.btn1.addActionListener((ActionEvent e) -> {
            try {
                outStream.writeUTF(mP.getText());
            } 
            catch (IOException ex) {
                System.out.println(ex);
                System.exit(-1);
            }
            
            mP.addText(user);
        });
        
        // Metodo per richiedere l'username all'utente
        user = askPopUp("Username", "Inserire nome utente");
        
        try {
            // Invio nome utente al server
            outStream.writeUTF(user);
        }
        catch (IOException ex) {
            System.out.println(ex);
            System.exit(-1);
        }
        
        // Creazione e avvio thread di ascolto verso il server
        listeningThread = new Thread(new InputThread());
        listeningThread.start();
    }

    /** Metodo per la creazione della finestra popup per la richiesta dello username
     *
     * @param nameWindow Nome della finestra popup
     * @param text Testo da inserire all'interno della finestra
     * @return Testo inserito dall'utente
     */
    public String askPopUp(String nameWindow, String text) {
        JFrame frame = new JFrame(nameWindow);
        return JOptionPane.showInputDialog(frame, text);
    }
    
    /** Metodo per prendere l'username dell'utente
     *
     * @return Username dell'utente
     */
    public String getUserName() {
        return user;
    }
    
    /** Metodo per acquisire l'oggetto creato per l'interfaccia
     *
     * @return Oggetto dell'interfaccia
     */
    public MainPanel getPanelClass() {
        return mP;
    }
    
    // Classe per l'ascolto dei messaggi in arrivo dal server
    class InputThread implements Runnable {
        @Override
        public void run() {
            while(true) {
                try {
                    // Ascolto nome utente e messaggio inviato da esso
                    String user = inStream.readUTF();
                    String text = inStream.readUTF();
                    
                    // Scrittura delle nome utente e messaggio
                    
                    mP.addText(user, text);
                }
                catch (IOException ex) {
                    System.out.println(ex);
                    System.exit(-1);
                }
            }
        }
    }
}
