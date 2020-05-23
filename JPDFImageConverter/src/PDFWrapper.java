import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDFreeTextAppearanceHandler;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFWrapper {
    private File pdfFile;
    private String fileName;
    private PDDocument pdfDoc;

    public PDFWrapper(File file) {
        try {
            pdfDoc = PDDocument.load(file);
            fileName = file.getName().substring(0, file.getName().indexOf("."));
            pdfFile = file;
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }

    public BufferedImage toImage(int page, int dpi) {
        BufferedImage image = null;
        try {
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);
            image = pdfRenderer.renderImageWithDPI(page, dpi);
        } catch (Exception e) {
            image = null;
        } finally {
            try {
                pdfDoc.close();
            } catch (IOException e) {}
            return image;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileName(int pagenum) {
        return fileName + pagenum;
    }
}
