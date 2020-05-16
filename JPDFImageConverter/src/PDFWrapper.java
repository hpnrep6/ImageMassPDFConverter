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
        try {
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);
            BufferedImage image = pdfRenderer.renderImageWithDPI(page, dpi);
            return image;
        } catch (Exception e) {
            return null;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileName(int pagenum) {
        return fileName + pagenum;
    }

    /*
    try (final PDDocument document = PDDocument.load(new File("/home/user/Downloads/010.pdf"))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String fileName = OUTPUT_DIR + "image-" + page + ".png";
                ImageIOUtil.writeImage(bim, fileName, 300);
            }
            document.close();
        } catch (IOException e) {
            System.err.println("Exception while trying to create pdf document - " + e);
        }
     */
}