import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.ExportXFDF;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Converter {
    private static String acceptedImageFiles = ".png .jpg .gif .bmp"; // file types supported by the imageIO.read function

    public static void ConvertPDFToImage(File directory, int dpi, File saveDirectory) {
        if(!directory.isDirectory()) {
            return;
        }
        File[] directoryFiles = getDirectoryFiles(directory);
        int directoryFileCount = directory.list().length;
        BufferedImage[] imageList = new BufferedImage[directoryFileCount];
        String[] fileNames = new String[directoryFileCount];
        for(int i = 0; i < directoryFileCount; i++) {
            if(directoryFiles[i].getName().substring(directoryFiles[i].getName().indexOf(".")).equals(".pdf")) {
                try {
                    PDFWrapper img = new PDFWrapper(directoryFiles[i]);
                    imageList[i] = img.toImage(0, dpi);
                    fileNames[i] =  directoryFiles[i].getName().substring(0, directoryFiles[i].getName().indexOf("."));
                } catch (Exception e) {
                }
            }
        }

        for(int i = 0; i < directoryFileCount; i++) {
            if(imageList[i] != null) {
                try {
                    Main.saveImage(imageList[i], saveDirectory.getAbsolutePath(), fileNames[i]);
                } catch (Exception e) {
                    System.out.println("Error saving " + fileNames[i]);
                }
            }
        }
    }

    public static void ConvertImageToPDF(File directory, File saveDirectory) {
        if(!directory.isDirectory()) {
            return;
        }
        File[] directoryFiles = getDirectoryFiles(directory);
        int directoryFileCount = directory.list().length;
        PDDocument[] pdfList = new PDDocument[directoryFileCount];
        String[] fileNames = new String[directoryFileCount];
        for(int i = 0; i < directoryFileCount; i++) {
            String fileType = directoryFiles[i].getName().substring(directoryFiles[i].getName().indexOf("."));
            if(acceptedImageFiles.contains(fileType.toLowerCase())) {
                try {
                    ImageWrapper pdf = new ImageWrapper(directoryFiles[i]);
                    pdfList[i] = pdf.toPDF(0);
                    fileNames[i] =  directoryFiles[i].getName().substring(0, directoryFiles[i].getName().indexOf("."));
                } catch (Exception e) {
                }
            }
        }

        for(int i = 0; i < directoryFileCount; i++) {
            if(pdfList[i] != null) {
                try {
                    Main.savePDF(pdfList[i], saveDirectory.getAbsolutePath(), fileNames[i]);
                } catch (Exception e) {
                    System.out.println("Error saving " + fileNames[i]);
                }
            }
        }
    }

    public static File[] getDirectoryFiles(File f) {
        String[] stringFiles = f.list();
        File[] listFiles = new File[stringFiles.length];
        for(int i = 0; i < listFiles.length; i++) {
            listFiles[i] = new File(f.toString() + File.separator + stringFiles[i]);
        }
        return listFiles;
    }

    public static File chooseDirectory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            return null;
        }

    }

}
