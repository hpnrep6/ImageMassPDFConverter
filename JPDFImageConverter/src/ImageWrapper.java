import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageWrapper {
    private File imageFile;
    private BufferedImage bufferedImage;
    private String fileName;
    private int width, height;

    public ImageWrapper(File file) {
        try {
            bufferedImage = ImageIO.read(file);
            width = bufferedImage.getWidth();
            height = bufferedImage.getHeight();
            imageFile = file;
            fileName = file.getName().substring(0,file.getName().indexOf("."));
        } catch (IOException e) {}
    }

    public PDDocument toPDF(int page) {
        try {
            PDDocument doc = new PDDocument();
            PDPage pdfPage = new PDPage(new PDRectangle(width, height));
            doc.addPage(pdfPage);
            PDImageXObject image = PDImageXObject.createFromFileByContent(imageFile, doc);
            PDPageContentStream contentStream = new PDPageContentStream(doc, pdfPage);
            contentStream.drawImage(image, 0,0);
            contentStream.close();
            return doc;
        } catch (Exception e) {
            return null;
        }
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    /*
    PDDocument document = new PDDocument();
    InputStream in = new FileInputStream(someImage);
    BufferedImage bimg = ImageIO.read(in);
    float width = bimg.getWidth();
    float height = bimg.getHeight();
    PDPage page = new PDPage(new PDRectangle(width, height));
    document.addPage(page);
    PDXObjectImage img = new PDJpeg(document, new FileInputStream(someImage));
    PDPageContentStream contentStream = new PDPageContentStream(document, page);
    contentStream.drawImage(img, 0, 0);
    contentStream.close();
    in.close();

    document.save("test.pdf");
    document.close();
     */
}
