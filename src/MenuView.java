import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    public MenuView() {
        setTitle("필터 및 사이즈 선택");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("맑은 고딕", Font.PLAIN, 15);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(8, 8, 8, 8);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        JButton meanFilter1Button = new JButton("Mean Filter(3x3)");
        meanFilter1Button.setFont(font);
        meanFilter1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainView(0);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(meanFilter1Button, gbc);

        JButton meanFilter2Button = new JButton("Mean Filter(5x5)");
        meanFilter2Button.setFont(font);
        meanFilter2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainView(1);
            }
        });
        gbc.gridy = 1;
        container.add(meanFilter2Button, gbc);

        JButton medianFilter1Button = new JButton("Median Filter(3x3)");
        medianFilter1Button.setFont(font);
        medianFilter1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainView(2);
            }
        });
        gbc.gridy = 2;
        container.add(medianFilter1Button, gbc);

        JButton medianFilter2Button = new JButton("Median Filter(5x5)");
        medianFilter2Button.setFont(font);
        medianFilter2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainView(3);
            }
        });
        gbc.gridy = 3;
        container.add(medianFilter2Button, gbc);

        JButton laplacianFilterButton = new JButton("LaplacianFilter(3x3)");
        laplacianFilterButton.setFont(font);
        laplacianFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainView(4);
            }
        });
        gbc.gridy = 4;
        container.add(laplacianFilterButton, gbc);

        JButton laplacianGaussianFilterButton = new JButton("LoGFilter(3x3)");
        laplacianGaussianFilterButton.setFont(font);
        laplacianGaussianFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainView(5);
            }
        });
        gbc.gridy = 5;
        container.add(laplacianGaussianFilterButton, gbc);

        pack();
        setVisible(true);
    }
}
