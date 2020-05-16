import org.apache.pdfbox.pdmodel.PDDocument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    private static final String OUTPUT_DIR = "/home/user/Downloads/";

    public static void main(String[] args) throws Exception {
        //Converter.ConvertPDFToImage(new File(OUTPUT_DIR), 300);
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
