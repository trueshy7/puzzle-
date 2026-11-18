package com.trueshy7.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    final int[][] winData = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
    int[][] data;
    //记录空白图片在t二维数组的位置
    int x, y;//
    private int stepCount = 0;
    String imagePath = "./image";
    //默认分类
    String category = "/animal/animal1/";
    String winPath = "./image/win.png";
    String aboutPath = "./image/about.png";

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem aboutItem = new JMenuItem("联系方式");

    JMenu changeImageMenu = new JMenu("跟换图片");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");

    public GameJFrame() {
        initJFrame();

        initJMenu();

        initData();

        initStepCount();

        initImage();


        setVisible(true);
    }

    private void initStepCount() {
        JLabel stepCountLabel = new JLabel("Step Count: " + stepCount);
        stepCountLabel.setBounds(50, 50, 100, 20);
        this.getContentPane().add(stepCountLabel);
        this.getContentPane().repaint();
    }

    private void initData() {
        stepCount = 0;
        this.data = new int[4][4];
        Random random = new Random();
        int[] data = new int[16];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }
        for (int i = 0; i < data.length; i++) {
            int index = random.nextInt(data.length);
            int temp = data[index];
            data[index] = data[i];
            data[i] = temp;
        }
        //找到空白图片的位置并把其他图片所在位置存好
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) {
                x = i / 4;
                y = i % 4;
            } else {
                this.data[i / 4][i % 4] = data[i];
            }

        }
    }

    private void initImage() {
        this.getContentPane().removeAll();
        if (victory()) {
            JLabel winImage = new JLabel(new ImageIcon(winPath));
            winImage.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winImage);
            this.getContentPane().repaint();
        }
        final int pixelLen = 105;
        final int rowCounts = 4;
        final int colCounts = 4;
        initStepCount();
        for (int i = 0; i < rowCounts; i++) {
            for (int j = 0; j < colCounts; j++) {
                int number = this.data[i][j];
                ImageIcon imageIcon = new ImageIcon(imagePath + category + number + ".jpg");
                JLabel imageLabel = new JLabel(imageIcon);
                imageLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                imageLabel.setBounds(j * pixelLen + 90, i * pixelLen + 163, pixelLen, pixelLen);
                this.getContentPane().add(imageLabel);
            }
        }
        ImageIcon backgroundImageIcon = new ImageIcon("./image/background.png");
        JLabel backgroundImageLabel = new JLabel(backgroundImageIcon);
        backgroundImageLabel.setBounds(0, 0, 600, 700);
        this.getContentPane().add(backgroundImageLabel);
        this.getContentPane().repaint();
    }


    private void initJMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu functionMenu = new JMenu("功能");
        JMenu aboutMenu = new JMenu("关于我们");

        replayItem.addActionListener(this);
        closeItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        aboutItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        functionMenu.add(changeImageMenu);
        changeImageMenu.add(girl);
        changeImageMenu.add(animal);
        changeImageMenu.add(sport);
        functionMenu.add(replayItem);
        functionMenu.add(reLoginItem);
        functionMenu.add(closeItem);
        aboutMenu.add(aboutItem);
        menuBar.add(functionMenu);
        menuBar.add(aboutMenu);


        this.setJMenuBar(menuBar);
    }

    private void initJFrame() {
        setSize(600, 670);
        setTitle("拼图小游戏");
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);

        this.setLayout(null);
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != winData[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (victory()) {
            return;
        }
        int code = e.getKeyCode();
        //按下A查看图片
        if (code == 65) {
            this.getContentPane().removeAll();
            JLabel allImage = new JLabel(new ImageIcon("./image/girl/girl1/all.jpg"));
            allImage.setBounds(90, 163, 400, 400);
            this.getContentPane().add(allImage);
            ImageIcon backgroundImageIcon = new ImageIcon("./image/background.png");
            JLabel backgroundImageLabel = new JLabel(backgroundImageIcon);
            backgroundImageLabel.setBounds(0, 0, 600, 700);
            this.getContentPane().add(backgroundImageLabel);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            return;
        }
        int code = e.getKeyCode();
        if (code == 37 && y + 1 < 4) {
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            stepCount++;
            initImage();

        } else if (code == 38 && x + 1 < 4) {
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            stepCount++;
            initImage();

        } else if (code == 39 && y - 1 >= 0) {
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            stepCount++;
            initImage();

        } else if (code == 40 && x - 1 >= 0) {
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            stepCount++;
            initImage();

        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            data = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0},};
            initImage();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            initData();
            initImage();
        } else if (obj == reLoginItem) {
            this.setVisible(false);
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.exit(0);
        } else if (obj == aboutItem) {
            JDialog dialog = new JDialog();
            JLabel aboutJLabel = new JLabel(new ImageIcon(aboutPath));
            aboutJLabel.setBounds(0, 0, 258, 258);
            dialog.getContentPane().add(aboutJLabel);
            dialog.setAlwaysOnTop(true);
            dialog.setSize(344, 344);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
        } else if (obj == girl) {
            initData();
            Random random = new Random();
            int index = random.nextInt(13) + 1;
            this.category = "/girl/girl" + index + "/";
            initImage();
        } else if (obj == sport) {
            initData();
            Random random = new Random();
            int index = random.nextInt(9) + 1;
            this.category = "/sport/sport" + index + "/";
            initImage();
        } else if (obj == animal) {
            initData();
            Random random = new Random();
            int index = random.nextInt(8) + 1;
            this.category = "/animal/animal" + index + "/";
            initImage();
        }

    }
}
