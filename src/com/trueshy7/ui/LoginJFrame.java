package com.trueshy7.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements KeyListener, ActionListener, MouseListener {
    static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new User("admin", "123456"));
    }

    JLabel rightCode = new JLabel();
    JButton confirmButton = new JButton();
    JButton registerButton = new JButton();
    JLabel userNameText = new JLabel(new ImageIcon());
    JLabel passwordText = new JLabel(new ImageIcon());
    String codeStr;
    JTextField username = new JTextField();
    JTextField password = new JTextField();

    public LoginJFrame() {
        initJFrame();

        initView();

        setVisible(true);
    }

    private void initView() {
        //添加用户名图片和密码图片
        String userNameImagePath = "./image/login/用户名.png";
        String passwordImagePath = "./image/login/密码.png";
        userNameText = new JLabel(new ImageIcon(userNameImagePath));
        passwordText = new JLabel(new ImageIcon(passwordImagePath));
        userNameText.setBounds(116, 135, 47, 17);
        passwordText.setBounds(130, 195, 32, 16);
        //添加用户名输入框和密码输入框
        username = new JTextField();
        username.setBounds(195, 134, 200, 30);
        password = new JTextField();
        password.setBounds(195, 195, 200, 30);

        this.getContentPane().add(userNameText);
        this.getContentPane().add(username);
        this.getContentPane().add(passwordText);
        this.getContentPane().add(password);

        initRightCode();

        //登录按钮
        ImageIcon confirmButtonImageIcon = new ImageIcon("./image/login/登录按钮.png");
        confirmButton = new JButton(new ImageIcon(confirmButtonImageIcon.getImage()));
        confirmButton.setBounds(123, 310, 128, 47);
        confirmButton.setBorderPainted(false);
        confirmButton.setContentAreaFilled(false);
        this.getContentPane().add(confirmButton);

        //注册按钮
        ImageIcon registerButtonImageIcon = new ImageIcon("./image/login/注册按钮.png");
        registerButton = new JButton(new ImageIcon(registerButtonImageIcon.getImage()));
        registerButton.setBounds(256, 310, 128, 47);
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        this.getContentPane().add(registerButton);

        JLabel backgroundLabel = new JLabel(new ImageIcon("./image/login/background.png"));
        backgroundLabel.setBounds(0, 0, 470, 390);
        this.getContentPane().add(backgroundLabel);
        //注册监听器
        confirmButton.addActionListener(this);
        registerButton.addActionListener(this);
        rightCode.addMouseListener(this);

    }

    private void initRightCode() {
        String codePath = "./image/login/验证码.png";
        JLabel codeTextImage = new JLabel(new ImageIcon(codePath));
        codeTextImage.setBounds(95, 256, 100, 30);
        //验证码框：
        JTextField code = new JTextField();
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);
        this.getContentPane().add(codeTextImage);
        //获得验证码
        codeStr = CodeUtil.getCodeText();

        rightCode.setBounds(300, 256, 50, 30);
        rightCode.setText(codeStr);

        this.getContentPane().add(rightCode);
        this.getContentPane().repaint();
    }


    private void initJFrame() {
        //主界面
        setSize(600, 670);
        setTitle("拼图小游戏登录");
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setLayout(null);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private boolean checkInfo() {
        for (User user : LoginJFrame.users) {
            if (user.getName().equals(username.getText()) && user.getPassword().equals(password.getText())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCode() {
        return rightCode.getText().equals(codeStr);
    }

    public void showJDialog(String content) {
        JDialog dialog = new JDialog();
        dialog.setSize(600, 670);
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        dialog.getContentPane().add(warning);

        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == confirmButton) {
            if (checkCode()) {
                if (checkInfo()) {
                    this.setVisible(false);
                    new GameJFrame();
                } else {
                    showJDialog("用户名或密码不正确");
                }
            } else {
                showJDialog("验证码不正确");
            }
        } else if (obj == registerButton) {
            String name = this.username.getText();
            String password = this.password.getText();
            if (name.isEmpty() || password.isEmpty()) {
                showJDialog("用户或密码不能为空");
                return;
            }
            LoginJFrame.users.add(new User(name, password));
            System.out.println(LoginJFrame.users.size());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == rightCode) {
            codeStr = CodeUtil.getCodeText();
            rightCode.setText(codeStr);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
