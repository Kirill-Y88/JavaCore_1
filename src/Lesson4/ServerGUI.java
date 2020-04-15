package Lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
    private static final int POS_X = 1000;
    private static final int POS_Y = 550;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;

    private final ChatServer server;
    private  final JButton btnStart = new JButton ("Start");
    private  final JButton btnStop = new JButton( " Stop");

    public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerGUI();
            }
        });

//throw new RuntimeException("Main died!");

    }
    ServerGUI(){
        Thread.setDefaultUncaughtExceptionHandler(this);
    setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);
    setTitle("Chat server");
    setAlwaysOnTop(true);
    setLayout(new GridLayout(1 , 2));
    btnStart.addActionListener(this);
    btnStop.addActionListener(this);
    add(btnStart);
    add(btnStop);

    server = new ChatServer();


    setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src==btnStart){
            server.start(8189);
        }
        else if (src==btnStop){
            int a = 5/0;
            //server.stop();
        }
        else {
            throw new RuntimeException("Unknown source:" + src);
        }

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //System.out.println("Hello hell");
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = String.format("Exception in \"%s\" %s: %s\n\tat %s",
                t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        JOptionPane.showMessageDialog(this, msg, "Excception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);

    }
}
