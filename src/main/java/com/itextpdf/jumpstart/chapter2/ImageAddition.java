package com.itextpdf.jumpstart.chapter2;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.File;
import java.io.IOException;

public class ImageAddition {
    public static final String DEST = "results/chapter02/tests.pdf";
    public static final String JOCA = "src/main/resources/img/joca.jpeg";
    public static final String ZECA = "src/main/resources/img/zeca.jpeg";

    public static void main(String[] args) throws IOException {

        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImageAddition().editPdf(DEST);
    }

    public static void editPdf(String dest) throws IOException{
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);

        PdfPage page = pdf.getPage(1);
        PdfCanvas canvas = new PdfCanvas(page);

        addImage(document, JOCA, ZECA);

        document.close();
    }

    public static void addImage(Document document, String imagePath1, String imagePath2) throws IOException{
        ImageData imageData1 = ImageDataFactory.create(imagePath1);
        Image image1 = new Image(imageData1);
        image1.scaleToFit(200, 200);
        image1.setFixedPosition(100, 400);
        document.add(image1);

        ImageData imageData2 = ImageDataFactory.create(imagePath2);
        Image image2 = new Image(imageData2);
        image2.scaleToFit(200,200);
        image2.setFixedPosition(300,400);
        document.add(image2);
    }



}
