package site.prodigal.ui;

import site.prodigal.client.TcpClient;
import site.prodigal.entity.Action;
import site.prodigal.serialization.Protocol;
import site.prodigal.utils.ObjectUtils;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class LoginUI {
    private static TcpClient client = null;
    public static void main(String[] args) {
        JFrame frame = new JFrame("登录界面");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel,frame);
        frame.setVisible(true);
        client = new TcpClient();
    }

    private static void placeComponents(JPanel panel,JFrame frame) {

        panel.setLayout(null);

        JLabel userLabel = new JLabel("用户名:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        JButton loginButton = new JButton("登录");
        loginButton.setBounds(100, 90, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = passwordText.getText();
                if (ObjectUtils.isEmpty(username)){
                    JOptionPane.showMessageDialog(panel, "请输入用户名....");
                    return;
                }
                if (ObjectUtils.isEmpty(password)){
                    JOptionPane.showMessageDialog(panel, "请输入密码....");
                    return;
                }
                Action action = new Action("/login", new Object[]{username, password});
                client.sendMsg(Protocol.toJsonStr(action));

                if ("true".equals(client.receiveMsg().replace("\r\n",""))){
                    new ChatUI(username,client);
                    frame.dispose();
                }else {
                    JOptionPane.showMessageDialog(panel, "用户名或者密码错误....");
                }
            }
        });
    }
}
