import org.apache.pdfbox.pdmodel.PDDocument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        new ConverterGUI();
    }

    public static void saveImage(BufferedImage image, String directory, String name) {
        try{
            File file = new File(directory + File.separator + name + ".png");
            ImageIO.write(image,"png", file);
        }catch(IOException e){}
    }

    public static void savePDF(PDDocument pdf, String directory, String name) {
        try {
            File file = new File(directory + File.separator + name + ".pdf");
            pdf.save(file);
        } catch(IOException e) {}
    }
}
