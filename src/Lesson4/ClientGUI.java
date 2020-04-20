package Lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private static FileOutputStream fileOutputStream;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top", true);
    private final JTextField tfLogin = new JTextField("ivan");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList<>();


    public static void main(String[] args)  {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });


    }

    ClientGUI(){
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setAlwaysOnTop(true);
        userList.setListData(new String[]{"user1", "user2", "user3", "user4",
                "user5", "user6", "user7", "user8", "user9",
                "user-with-exceptionally-long-name-in-this-chat"});
        JScrollPane scrUser = new JScrollPane(userList);
        JScrollPane scrLog = new JScrollPane(log);
        scrUser.setPreferredSize(new Dimension(100, 0));
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEnabled(false);
        cbAlwaysOnTop.addActionListener(this);
        btnSend.addActionListener(this);

        try {
            fileOutputStream = new FileOutputStream("C:\\Users\\benbe\\Note.txt", true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//отправка сообщения по нажатию ентера
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    log.append(" \n" + tfMessage.getText());
                    tfMessage.setText("");}
            }
        };

        tfMessage.addKeyListener(keyListener);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(scrUser, BorderLayout.EAST);
        add(scrLog, BorderLayout.CENTER);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);



        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src==cbAlwaysOnTop){
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        }
        //отправка сообщения по нажатию сенда
        else if(src == btnSend){
            log.append(" \n" + tfMessage.getText());
            tfMessage.setText("");

            //в файл информмация не пишется
            try {
                fileOutputStream.write((tfMessage.getText()).getBytes());
                fileOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

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