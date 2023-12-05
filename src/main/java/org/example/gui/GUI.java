package org.example.gui;

import org.example.habr.HabrParser;
import org.example.habr.HabrSettings;
import org.example.model.HabrArticle;
import org.example.service.*;

import javax.annotation.processing.Messager;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class GUI extends JFrame implements OnCompleted, OnNewDataHandler<HabrArticle> {
    private JTextPane textArea = new JTextPane();
    private StyledDocument textDocument;
    private Thread downloadThread;

    public GUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setTitle("Habr");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //region Создание панели поиска
        JPanel rightPanel = new JPanel();

        rightPanel.setPreferredSize(new Dimension(250, rightPanel.getHeight()));
        GridLayout layout = new GridLayout(0, 1, 5, 12);
        rightPanel.setLayout(layout);

        rightPanel.add(new JLabel("Первая страница"));

        JTextField textStart = new JTextField(10);
        JTextField textEnd = new JTextField(10);
        JButton startButton = new JButton("Start");
        JButton abortButton = new JButton("Abort");

        rightPanel.add(textStart);
        rightPanel.add(textEnd);
        rightPanel.add(startButton);
        rightPanel.add(abortButton);

        add(rightPanel, BorderLayout.EAST);
        //endregion

        //region Создание парсера
        ParserWorker<ArrayList<HabrArticle>> parser = new ParserWorker<>(new HabrParser());
        parser.onCompletedList.add(this);
        parser.onNewDataList.add(this);
        //endregion

        getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        textArea.setEditable(false);
        textDocument = textArea.getStyledDocument();

        startButton.addActionListener(e -> {
            int start = Integer.parseInt(textStart.getText());
            int end = Integer.parseInt(textEnd.getText());
            parser.setParserSettings(new HabrSettings(start, end));
            downloadThread = new Thread(() -> {
                try {
                    parser.Start();
                    //Thread.sleep(1000);
                    //parser.Abort();
                } catch (Exception thrown) {
                    thrown.printStackTrace();
                }
            });
            downloadThread.start();
        });
        abortButton.addActionListener(e -> {
            if (downloadThread != null && downloadThread.isAlive()) {
                parser.Abort();
                downloadThread.interrupt();
            }
        });
    }

    @Override
    public void OnNewData(Object sender, HabrArticle article) {
        if (!article.isNull()) {
            try {
                textDocument = textArea.getStyledDocument();
                SimpleAttributeSet titleStyle = new SimpleAttributeSet();
                StyleConstants.setBold(titleStyle, true);
                StyleConstants.setFontSize(titleStyle, 18);
                textDocument.insertString(textDocument.getLength(), article.getTitle() + "\n", titleStyle);

                SimpleAttributeSet imageStyle = new SimpleAttributeSet();
                BufferedImage image = ImageIO.read(new File(article.getPathToImage()));
                ImageIcon icon = new ImageIcon(image);
                StyleConstants.setIcon(imageStyle, icon);
                textDocument.insertString(textDocument.getLength(), " ", imageStyle);

                textDocument.insertString(textDocument.getLength(), "\n", titleStyle);

                SimpleAttributeSet textStyle = new SimpleAttributeSet();
                StyleConstants.setFontSize(titleStyle, 12);
                textDocument.insertString(textDocument.getLength(), article.getText() + "\n", textStyle);

                pack();
                setLocation(350, 150);
                setSize(800, 500);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void OnCompleted(Object sender) {
        try {
            textDocument = textArea.getStyledDocument();
            SimpleAttributeSet titleStyle = new SimpleAttributeSet();
            StyleConstants.setBold(titleStyle, true);
            StyleConstants.setFontSize(titleStyle, 18);
            textDocument.insertString(textDocument.getLength(), "Загрузка окончена!", titleStyle);
        } catch (Exception e) {

        }
    }
}
