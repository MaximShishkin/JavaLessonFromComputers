package ru.shishkin.javaLessonFromComputer12.simplePuzzle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class PanelSP extends JPanel {
    private int x = 3, y = 3;
    private int napr = 2;
    private Image img;
    protected JButton btn1, btn2, btn3;
    protected Image[] mas = new Image[15];
    protected Timer timerUpdate;
    protected int[][] data = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0},
    };

    private class myMouse1 implements MouseListener {
        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}

        public void mouseReleased(MouseEvent e) {
            int tX = e.getX();
            int tY = e.getY();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (tX == (10 + i * 70)) {
                        if (tY == (10 + i * 70)) ;
                    }
                }
            }
        }

        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    private class myMouse2 implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }

    public PanelSP() {
        addMouseListener(new myMouse1());
        addMouseMotionListener(new myMouse2());
        setLayout(null);

        btn1 = new JButton("START");
        btn1.setForeground(Color.RED);
        btn1.setBounds(500, 50, 100, 30);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerUpdate.stop();
                for (int i = 0; i < 10000; i++) {
                    int t = (int) (Math.random() * 4);
                    if ((t == 0) && (y > 0))
                    {
                        data[y][x] = data[y - 1][x];
                        data[y - 1][x] = 0;
                        y--;
                    }
                    if ((t == 1) && (y < 3)) {
                        data[y][x] = data[y + 1][x];
                        data[y + 1][x] = 0;
                        y++;
                    }
                    if ((t == 2) && (x > 0)) {
                        data[y][x] = data[y][x - 1];
                        data[y][x - 1] = 0;
                        x--;
                    }
                    if ((t == 3) && (x < 3)) {
                        data[y][x] = data[y][x + 1];
                        data[y][x + 1] = 0;
                        x++;
                    }
                }
            }
        });
        add(btn1);

        setLayout(null);
        btn2 = new JButton("SLOW");
        btn2.setForeground(Color.BLUE);
        btn2.setBounds(500, 100, 100, 30);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerUpdate.start();
            }
        });
        add(btn2);

        setLayout(null);
        btn3 = new JButton("STOP");
        btn3.setForeground(Color.GREEN);
        btn3.setBounds(500, 150, 100, 30);
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerUpdate.stop();
            }
        });
        add(btn3);

        try {
            img = ImageIO.read(new File("./fon.png"));
            for (int i = 0; i < 15; i++) {
                try {
                    mas[i] = ImageIO.read(new File("./k" + (i + 1) + ".png"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        Timer nt = new Timer(25, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        nt.start();

        timerUpdate = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int t = (int) (Math.random() * 4);
                if ((t == 0) && (y > 0))
                {
                    data[y][x] = data[y - 1][x];
                    data[y - 1][x] = 0;
                    y--;
                }
                if ((t == 1) && (y < 3)) {
                    data[y][x] = data[y + 1][x];
                    data[y + 1][x] = 0;
                    y++;
                }
                if ((t == 2) && (x > 0)) {
                    data[y][x] = data[y][x - 1];
                    data[y][x - 1] = 0;
                    x--;
                }
                if ((t == 3) && (x < 3)) {
                    data[y][x] = data[y][x + 1];
                    data[y][x + 1] = 0;
                    x++;
                }
            }
        });
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.drawImage(img, 0, 0, null);
        gr.setColor(Color.WHITE);
        for (int i = 0; i <= 4; i++) {
            gr.drawLine(10 + i * 70, 10, 10 + i * 70, 290);
            gr.drawLine(10, 10 + i * 70, 290, 10 + i * 70);
        }
        gr.setColor(Color.YELLOW);
        gr.setFont(new Font("arial", 0, 40));
        gr.drawString("Score: 0", 450, 300);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j] != 0)
                    gr.drawImage(mas[data[i][j] - 1], 10 + j * 70, 10 + i * 70, null);
            }
        }
    }
}
