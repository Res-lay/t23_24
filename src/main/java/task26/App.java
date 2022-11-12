package task26;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
        public static void main(String[] args)
        {
            String srcFolder = "src/main/java/task26/img";
            String dstFolder = "src/main/java/task26/new_img";

            File srcDir = new File(srcFolder);

            long start = System.currentTimeMillis();

            File[] files = srcDir.listFiles();

            try
            {
                if (!Files.exists(Paths.get(dstFolder)))
                {
                    Files.createDirectories(Paths.get(dstFolder));
                }
                System.out.println(Runtime.getRuntime().availableProcessors());
                for(File file : files)
                {

                    if(Runtime.getRuntime().availableProcessors() != 0)
                    {
                        new Thread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        BufferedImage image = null;
                                        try {
                                            image = ImageIO.read(file);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }

                                        int newWidth = image.getWidth() / 2;
                                        int newHeight = (int) Math.round(
                                                image.getHeight() / (image.getWidth() / (double) newWidth)
                                        );
                                        BufferedImage newImage = new BufferedImage(
                                                newWidth, newHeight, BufferedImage.TYPE_INT_RGB
                                        );

                                        int widthStep = image.getWidth() / newWidth;
                                        int heightStep = image.getHeight() / newHeight;

                                        for (int x = 0; x < newWidth; x++)
                                        {
                                            for (int y = 0; y < newHeight; y++) {
                                                int rgb = image.getRGB(x * widthStep, y * heightStep);
                                                newImage.setRGB(x, y, rgb);
                                            }
                                        }

                                        File newFile = new File(dstFolder + "/" + file.getName());
                                        try {
                                            ImageIO.write(newImage, "jpg", newFile);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                        ).start();
                    }

                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Duration: " + (System.currentTimeMillis() - start));
        }
}
