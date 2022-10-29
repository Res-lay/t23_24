package org.example;


import org.jsoup.Jsoup;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class App
{
    public static void main( String[] args )
    {
        try{
            String [] substr;
            InputStream in;
            org.jsoup.nodes.Document document = Jsoup.connect("https://www.mirea.ru/").get();
            org.jsoup.select.Elements titleElements = document.select("img");
            for (org.jsoup.nodes.Element element : titleElements){
//                path = "D:\\Studying\\untitled2\\images\\";
                String srcelem = element.attr("abs:src");
                if(srcelem != "" && (srcelem.contains(".png") || srcelem.contains(".svg") || srcelem.contains(".jpg") || srcelem.contains(".webp"))){
                    System.out.println(srcelem);
                    substr = srcelem.split("/");
                    URL url = new URL(srcelem);
                    in = url.openStream();
                    Files.copy(in, Paths.get("D:\\Studying\\untitled2\\images\\" + substr[substr.length - 1]), StandardCopyOption.REPLACE_EXISTING);
                    in.close();
//                    img = ImageIO.read(url);
//                    substr = srcelem.split("/");
//                    System.out.println(substr[substr.length - 1]);
//                    expansion = substr[substr.length - 1].split("\\.");
//                    System.out.println(expansion.length);
//                    path += substr[substr.length - 1];
//                    File file = new File(path);
//                    if(img != null) {
//                        ImageIO.write(img, expansion[expansion.length - 1], file);
//                    }
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
