package ru.shishkin.paint;

import javax.swing.*;
import java.awt.*;

class FramePaint extends JFrame {
    public FramePaint() {
        setBounds(0, 0, 800, 600);
        setTitle("������ � �����");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        PanelPaint pan = new PanelPaint();
        Container con = getContentPane();
        con.add(pan);
        setVisible(true);
    }
}