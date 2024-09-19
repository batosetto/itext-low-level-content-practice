package com.itextpdf.jumpstart.chapter2;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class CanvasTests {

    public static final String DEST = "results/chapter02/tests.pdf";
    public static final String JOCA = "src/main/resources/img/joca.jpeg";
    public static final String ZECA = "src/main/resources/img/zeca.jpeg";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CanvasTests().createPdf(DEST);
    }

    public void createPdf(String dest) throws java.io.IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        PageSize ps = PageSize.A4;
        PdfPage page = pdf.addNewPage(ps);
        PdfCanvas canvas = new PdfCanvas(page);

        Color greenColor = new DeviceCmyk(1.f, 0.f, 1.f, 0.176f);
        Color blueColor = new DeviceCmyk(1.f, 0.156f, 0.f, 0.118f);

        drawBorder(canvas, ps, 20, greenColor);
        drawBorder(canvas, ps, 40, blueColor);
        addImage(document, JOCA, ZECA);
        createText(canvas, ps);

        pdf.close();

    }

    public void drawBorder(PdfCanvas canvas, PageSize ps, float marginOffset, Color color) throws IOException {

        canvas.setLineWidth(2)
                .setStrokeColor(color)
                .rectangle(
                        marginOffset,
                        marginOffset,
                        ps.getWidth() - 2 * marginOffset,
                        ps.getHeight() - 2 * marginOffset
                ).stroke();

    }

    public static void addImage(Document document, String imagePath1, String imagePath2) throws IOException{
        Color redColor = new DeviceCmyk(0.f, 1.f, 1.f, 0.f);
        Color lightGreeenColor = new DeviceCmyk(0.25f, 0.f, 0.25f, 0.f);

        ImageData imageData1 = ImageDataFactory.create(imagePath1);
        Image image1 = new Image(imageData1);
        image1.scaleToFit(250, 250);
        image1.setFixedPosition(100, 250);

        Paragraph title1 = new Paragraph("Joca - The custest and oldest")
                .setFixedPosition(100.0f, 500.0f, 200.0f)
                .setFontColor(redColor);
        document.add(image1);
        document.add(title1);

        ImageData imageData2 = ImageDataFactory.create(imagePath2);
        Image image2 = new Image(imageData2);
        image2.scaleToFit(250,250);
        image2.setFixedPosition(300,200);

        Paragraph title2 = new Paragraph("Zeca - The bravest and youngest")
                .setFixedPosition(300.0f, 450.0f, 200.0f)
                .setFontColor(lightGreeenColor);
        document.add(image2);
        document.add(title2);
    }

    public static void createText(PdfCanvas canvas, PageSize ps) throws com.itextpdf.io.IOException, IOException {
        ArrayList<String> text = new ArrayList<>();
        text.add("         Episode V         ");
        text.add("  THE EMPIRE STRIKES BACK  ");
        text.add("It is a dark time for the");
        text.add("Rebellion. Although the Death");
        text.add("Star has been destroyed,");
        text.add("Imperial troops have driven the");
        text.add("Rebel forces from their hidden");
        text.add("base and pursued them across");
        text.add("the galaxy.");
        text.add("Evading the dreaded Imperial");
        text.add("Starfleet, a group of freedom");
        text.add("fighters led by Luke Skywalker");
        text.add("has established a new secret");
        text.add("base on the remote ice world");
        text.add("of Hoth...");

        canvas.concatMatrix(1, 0, 0, 1, 0, ps.getHeight() - 10);
        canvas.beginText()
                .setFontAndSize(PdfFontFactory.createFont(FontConstants.COURIER_BOLD), 14)
                .setLeading(14 * 1.2f)
                .moveText(70, -40);
        for (String s : text) {
            //Add text and move to the next line
            canvas.newlineShowText(s);
        }
        canvas.endText();



    }
}
