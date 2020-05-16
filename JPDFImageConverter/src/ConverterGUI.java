import org.apache.pdfbox.contentstream.operator.state.Save;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ConverterGUI {
    private JButton DirectorySelect;
    private JButton SaveSelect;
    private JButton PDFtoIMG;
    private JButton IMGtoPDF;
    private JPanel mainPanel;
    private JTextArea UIMessage;
    private JTextField DPIInput;

    private int dpi = -1;
    private File selectedDirectory, saveDirectory;

    public ConverterGUI() {
        JFrame main = new JFrame("PDF Image Converter");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(300,200);
        main.add(mainPanel);
        main.setVisible(true);

        UIMessage.setEditable(false);
        DirectorySelect.setText("Select PDF/Image Directory");
        SaveSelect.setText("Select Save Directory");
        PDFtoIMG.setText("Convert PDF to PNG");
        IMGtoPDF.setText("Convert Image to PDF");
        DPIInput.setText("Enter DPI and press [Enter]");
        UIMessage.setText("");

        DirectorySelect.addActionListener(this::actionPerformed);
        SaveSelect.addActionListener(this::actionPerformed);
        PDFtoIMG.addActionListener(this::actionPerformed);
        IMGtoPDF.addActionListener(this::actionPerformed);
        DPIInput.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == DirectorySelect) {
            selectedDirectory = Converter.chooseDirectory();
        } else if (e.getSource() == SaveSelect) {
            saveDirectory = Converter.chooseDirectory();
        } else if(e.getSource() == DPIInput) {
            try {
                int dpiInput = Integer.parseInt(DPIInput.getText());
                if(dpiInput > 0) {
                    dpi = dpiInput;
                    UIMessage.setText("DPI Set");
                } else {
                    dpi = -1;
                    UIMessage.setText("Enter a number greater than 0");
                }
            } catch (Exception exception) {
                UIMessage.setText("Input not recognised as an integer");
                dpi = -1;
            }
        } else if(e.getSource() == PDFtoIMG) {
            if(!(selectedDirectory == null || saveDirectory == null || dpi < 0)) {
                Converter.ConvertPDFToImage(selectedDirectory, dpi, saveDirectory);
                UIMessage.setText("PDFs successfully converted");
            } else {
                UIMessage.setText("One of the input fields have not been set");
            }
        } else if(e.getSource() == IMGtoPDF) {
            if(!(selectedDirectory == null || saveDirectory == null)) {
                Converter.ConvertImageToPDF(selectedDirectory, saveDirectory);
                UIMessage.setText("Images successfully converted");
            } else {
                UIMessage.setText("One of the input fields have not been set");
            }
        }
    }
}
