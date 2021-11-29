import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainView extends JFrame {
    private BufferedImage inputImage = null;
    private Image inputDisplayImage = null;
    private Image outputImage = null;
    public MainView(int mode) {
        switch (mode) {
            case 0 -> setTitle("Mean Filter(3x3)");
            case 1 -> setTitle("Mean Filter(5x5)");
            case 2 -> setTitle("Median Filter(3x3)");
            case 3 -> setTitle("Median Filter(5x5");
            case 4 -> setTitle("Laplacian Filter(3x3)");
            case 5 -> setTitle("LoG Filter(3x3)");
        }
        setSize(800, 500);

        Font font = new Font("맑은 고딕", Font.PLAIN, 15);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(8, 8, 8, 8);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        JLabel inputTextLabel = new JLabel("입력");
        inputTextLabel.setFont(font);
        inputTextLabel.setVerticalAlignment(JLabel.BOTTOM);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        container.add(inputTextLabel, gbc);

        ImageIcon inputImageIcon = new ImageIcon();
        JLabel inputImageLabel = new JLabel(inputImageIcon);
        inputImageLabel.setSize(256, 256);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        container.add(inputImageLabel, gbc);

        JLabel outputTextLabel = new JLabel("출력");
        outputTextLabel.setFont(font);
        outputTextLabel.setVerticalAlignment(JLabel.BOTTOM);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 0;
        container.add(outputTextLabel, gbc);

        ImageIcon outputImageIcon = new ImageIcon();
        JLabel outputImageLabel = new JLabel(outputImageIcon);
        outputImageLabel.setSize(256, 256);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        container.add(outputImageLabel, gbc);

        JButton fileChooserButton = new JButton("파일 열기");
        fileChooserButton.setFont(font);
        fileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = fileOpen(fileChooserButton);
                if (filePath == null)
                    return;
                File inputImageFile = new File(filePath);
                try {
                    inputImage = ImageIO.read(inputImageFile);
                    inputDisplayImage = inputImage.getScaledInstance(256, 256, Image.SCALE_SMOOTH);
                    inputImageIcon.setImage(inputDisplayImage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                inputImageLabel.revalidate();
                inputImageLabel.repaint();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        container.add(fileChooserButton, gbc);

        JButton executeButton = new JButton("실행");
        executeButton.setFont(font);
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (mode) {
                    case 0 -> outputImage = ImageFilter.getInstance().meanFilter1(inputImage);
                    case 1 -> outputImage = ImageFilter.getInstance().meanFilter2(inputImage);
                    case 2 -> outputImage = ImageFilter.getInstance().medianFilter1(inputImage);
                    case 3 -> outputImage = ImageFilter.getInstance().medianFilter2(inputImage);
                    case 4 -> outputImage = ImageFilter.getInstance().laplacianFilter(inputImage);
                    case 5 -> outputImage = ImageFilter.getInstance().laplacianGaussianFilter(inputImage);
                }
                outputImage = outputImage.getScaledInstance(256, 256, Image.SCALE_SMOOTH);
                outputImageIcon.setImage(outputImage);
                outputImageLabel.revalidate();
                outputImageLabel.repaint();
                executeButton.setEnabled(false);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        container.add(executeButton, gbc);

        setVisible(true);
    }

    public String fileOpen(Component parent) {
        String filePath = "";

        JFileChooser fileChooser = new JFileChooser();
        setFileChooserFont(fileChooser.getComponents(), parent.getFont());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지 파일", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("파일 열기");
        fileChooser.setAcceptAllFileFilterUsed(false);

        int returnValue = fileChooser.showOpenDialog(parent);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getPath();
            return filePath;
        } else {
            return null;
        }
    }

    public void setFileChooserFont(Component[] comp, Font font) {
        for (Component component : comp) {
            if (component instanceof Container) setFileChooserFont(((Container) component).getComponents(), font);
            try {
                component.setFont(font);
            } catch (Exception ignored) {

            }
        }
    }
}
