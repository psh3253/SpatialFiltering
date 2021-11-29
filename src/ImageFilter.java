import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class ImageFilter {
    private static ImageFilter instance = null;

    private ImageFilter() {
    }

    public static ImageFilter getInstance() {
        if (instance == null)
            instance = new ImageFilter();
        return instance;
    }

    public BufferedImage meanFilter1(BufferedImage image) {
        double[][] mask = {
                {1.0 / 9, 1.0 / 9, 1.0 / 9},
                {1.0 / 9, 1.0 / 9, 1.0 / 9},
                {1.0 / 9, 1.0 / 9, 1.0 / 9}
        };
        int maxWidth = image.getWidth();
        int maxHeight = image.getHeight();
        for (int i = 1; i < maxHeight - 1; i++) {
            for (int j = 1; j < maxWidth - 1; j++) {
                int r = 0;
                int g = 0;
                int b = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        r += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getRed() * mask[k][l];
                        g += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getGreen() * mask[k][l];
                        b += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getBlue() * mask[k][l];
                    }
                }
                image.setRGB(j, i, new Color(r, g, b).getRGB());
            }
        }
        return image;
    }

    public BufferedImage meanFilter2(BufferedImage image) {
        double[][] mask = {
                {1.0 / 25, 1.0 / 25, 1.0 / 25, 1.0 / 25, 1.0 / 25},
                {1.0 / 25, 1.0 / 25, 1.0 / 25, 1.0 / 25, 1.0 / 25},
                {1.0 / 25, 1.0 / 25, 1.0 / 25, 1.0 / 25, 1.0 / 25},
                {1.0 / 25, 1.0 / 25, 1.0 / 25, 1.0 / 25, 1.0 / 25},
                {1.0 / 25, 1.0 / 25, 1.0 / 25, 1.0 / 25, 1.0 / 25}
        };
        int maxWidth = image.getWidth();
        int maxHeight = image.getHeight();
        for (int i = 2; i < maxHeight - 2; i++) {
            for (int j = 2; j < maxWidth - 2; j++) {
                int r = 0;
                int g = 0;
                int b = 0;
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        r += new Color(image.getRGB(j - 2 + l, i - 2 + k)).getRed() * mask[k][l];
                        g += new Color(image.getRGB(j - 2 + l, i - 2 + k)).getGreen() * mask[k][l];
                        b += new Color(image.getRGB(j - 2 + l, i - 2 + k)).getBlue() * mask[k][l];
                    }
                }
                image.setRGB(j, i, new Color(r, g, b).getRGB());
            }
        }
        return image;
    }

    public BufferedImage medianFilter1(BufferedImage image) {
        int maxWidth = image.getWidth();
        int maxHeight = image.getHeight();
        for (int i = 1; i < maxHeight - 1; i++) {
            for (int j = 1; j < maxWidth - 1; j++) {
                ArrayList<Integer> rList = new ArrayList<>();
                ArrayList<Integer> gList = new ArrayList<>();
                ArrayList<Integer> bList = new ArrayList<>();
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        rList.add(new Color(image.getRGB(j - 1 + l, i - 1 + k)).getRed());
                        gList.add(new Color(image.getRGB(j - 1 + l, i - 1 + k)).getGreen());
                        bList.add(new Color(image.getRGB(j - 1 + l, i - 1 + k)).getBlue());
                    }
                }
                Collections.sort(rList);
                Collections.sort(gList);
                Collections.sort(bList);
                int r = rList.get(9 / 2);
                int g = gList.get(9 / 2);
                int b = bList.get(9 / 2);
                image.setRGB(j, i, new Color(r, g, b).getRGB());
            }
        }
        return image;
    }

    public BufferedImage medianFilter2(BufferedImage image) {
        int maxWidth = image.getWidth();
        int maxHeight = image.getHeight();
        for (int i = 2; i < maxHeight - 2; i++) {
            for (int j = 2; j < maxWidth - 2; j++) {
                ArrayList<Integer> rList = new ArrayList<>();
                ArrayList<Integer> gList = new ArrayList<>();
                ArrayList<Integer> bList = new ArrayList<>();
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        rList.add(new Color(image.getRGB(j - 2 + l, i - 2 + k)).getRed());
                        gList.add(new Color(image.getRGB(j - 2 + l, i - 2 + k)).getGreen());
                        bList.add(new Color(image.getRGB(j - 2 + l, i - 2 + k)).getBlue());
                    }
                }
                Collections.sort(rList);
                Collections.sort(gList);
                Collections.sort(bList);
                int r = rList.get(25 / 2);
                int g = gList.get(25 / 2);
                int b = bList.get(25 / 2);
                image.setRGB(j, i, new Color(r, g, b).getRGB());
            }
        }
        return image;
    }

    public BufferedImage laplacianFilter(BufferedImage image) {
        double[][] mask = {
                {0, -1, 0},
                {-1, 4, -1},
                {0, -1, 0}
        };
        int maxWidth = image.getWidth();
        int maxHeight = image.getHeight();
        for (int i = 1; i < maxHeight - 1; i++) {
            for (int j = 1; j < maxWidth - 1; j++) {
                int r = 0;
                int g = 0;
                int b = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        r += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getRed() * mask[k][l];
                        g += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getGreen() * mask[k][l];
                        b += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getBlue() * mask[k][l];
                    }
                }
                if (r < 0) r = 0;
                if (g < 0) g = 0;
                if (b < 0) b = 0;
                if (r > 255) r = 255;
                if (g > 255) g = 255;
                if (b > 255) b = 255;
                image.setRGB(j, i, new Color(r, g, b).getRGB());
            }
        }
        return image;
    }

    public BufferedImage laplacianGaussianFilter(BufferedImage image) {
        double[][] gaussainMask = {
                {1.0 / 16, 1.0 / 8, 1.0 / 16},
                {1.0 / 8, 1.0 / 4, 1.0 / 8},
                {1.0 / 16, 1.0 / 8, 1.0 / 16}
        };
        double[][] laplacianMask = {
                {0, -1, 0},
                {-1, 4, -1},
                {0, -1, 0}
        };
        int maxWidth = image.getWidth();
        int maxHeight = image.getHeight();
        for (int i = 1; i < maxHeight - 1; i++) {
            for (int j = 1; j < maxWidth - 1; j++) {
                int r = 0;
                int g = 0;
                int b = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        r += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getRed() * gaussainMask[k][l];
                        g += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getGreen() * gaussainMask[k][l];
                        b += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getBlue() * gaussainMask[k][l];
                    }
                }
                image.setRGB(j, i, new Color(r, g, b).getRGB());
            }
        }
        for(int i = 1; i < maxHeight - 1; i++)
        {
            for(int j = 1; j < maxWidth - 1; j++) {
                int r = 0;
                int g = 0;
                int b = 0;
                for(int k = 0; k < 3; k++) {
                    for(int l = 0; l< 3; l++) {
                        r += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getRed() * laplacianMask[k][l];
                        g += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getGreen() * laplacianMask[k][l];
                        b += new Color(image.getRGB(j - 1 + l, i - 1 + k)).getBlue() * laplacianMask[k][l];
                    }
                }
                if (r < 0) r = 0;
                if (g < 0) g = 0;
                if (b < 0) b = 0;
                if (r > 255) r = 255;
                if (g > 255) g = 255;
                if (b > 255) b = 255;
                image.setRGB(j, i, new Color(r, g, b).getRGB());
            }
        }
        return image;
    }
}
