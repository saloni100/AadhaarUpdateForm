package com.example.example;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfHelper {

    public void createPdf( AadharData data){
        if(data!=null && !TextUtils.isEmpty(data.getFull_name())){
            String FILE = Environment.getExternalStorageDirectory().toString()
                    + "/PDF/" + data.getFull_name()+".pdf";
            // Create New Blank Document
            Document document = new Document(PageSize.A4);
            // Create Directory in External Storage
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/PDF");
            myDir.mkdirs();
            // Create Pdf Writer for Writting into New Created Document
            try {
                PdfWriter.getInstance(document, new FileOutputStream(FILE));
                // Open Document for Writting into document
                document.open();
                Paragraph pr = new Paragraph();
                pr.add("This is a pdf document");
                document.add(pr);
                //     User Define Method
                addMetaData(document);
                addTitlePage(document,data);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            document.close();
        }

    }

    // Set PDF document Properties
    public void addMetaData(Document document)
    {
        document.addTitle("Aadhaar Update Form");
        document.addSubject("Person Info");
        document.addKeywords("Personal");
        document.addAuthor("TAG");
        document.addCreator("TAG");
    }

    public void addTitlePage(Document document,  AadharData data) throws DocumentException {
        // Font Style for Document
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD
                | Font.UNDERLINE, BaseColor.GRAY);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        // Start New Paragraph
        Paragraph prHead = new Paragraph();
        // Set Font in this Paragraph
        prHead.setFont(titleFont);
        // Add item into Paragraph
        prHead.add("Aadhaar Update Form\n");
        prHead.setAlignment(Element.ALIGN_CENTER);

        Paragraph SubHead = new Paragraph();
        prHead.setFont(catFont);
        prHead.add("Personal Details\n");
        SubHead.setAlignment(Element.ALIGN_CENTER);

        String fullname = data.getFull_name();
        String uid = data.getUid();
        String mobileNumber = data.getMobile_no();
        String AadharNumber = data.getAadhaar_num();
        String gender = data.getGender();
        String address = data.getAddress();
        String email = data.getEmail();
        String pincode = data.getPincode();
        Bitmap image = data.getBitmapImage();
        if(image!=null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 50, stream);
            try {
                Image image1 = Image.getInstance(stream.toByteArray());
                document.add(image1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Create Table into Document with 1 Row
        PdfPTable myTable = new PdfPTable(2);
        // 100.0f mean width of table is same as Document size
        myTable.setWidthPercentage(100.0f);

        // Create cells
        PdfPCell cell1 = new PdfPCell(new Paragraph("Full Name", smallBold));
        PdfPCell cell2 = new PdfPCell(new Paragraph(fullname, smallBold));
        PdfPCell cell5 = new PdfPCell(new Paragraph("Email", smallBold));
        PdfPCell cell6 = new PdfPCell(new Paragraph(email, smallBold));
        PdfPCell cell7 = new PdfPCell(new Paragraph("Gender", smallBold));
        PdfPCell cell8 = new PdfPCell(new Paragraph(gender, smallBold));
        PdfPCell cell9 = new PdfPCell(new Paragraph("Mobile Number", smallBold));
        PdfPCell cell10 = new PdfPCell(new Paragraph(mobileNumber, smallBold));
        PdfPCell cell11 = new PdfPCell(new Paragraph("Aadhaar NUmber", smallBold));
        PdfPCell cell12 = new PdfPCell(new Paragraph(AadharNumber, smallBold));
        PdfPCell cell13 = new PdfPCell(new Paragraph("Uid", smallBold));
        PdfPCell cell14 = new PdfPCell(new Paragraph(uid, smallBold));
        PdfPCell cell15 = new PdfPCell(new Paragraph("Address", smallBold));
        PdfPCell cell16 = new PdfPCell(new Paragraph(address, smallBold));
        PdfPCell cell17 = new PdfPCell(new Paragraph("Pincode", smallBold));
        PdfPCell cell18 = new PdfPCell(new Paragraph(pincode, smallBold));

        // Add cells in table
        myTable.addCell(cell1);
        myTable.addCell(cell2);
        myTable.addCell(cell5);
        myTable.addCell(cell6);
        myTable.addCell(cell7);
        myTable.addCell(cell8);
        myTable.addCell(cell9);
        myTable.addCell(cell10);
        myTable.addCell(cell11);
        myTable.addCell(cell12);
        myTable.addCell(cell13);
        myTable.addCell(cell14);
        myTable.addCell(cell15);
        myTable.addCell(cell16);
        myTable.addCell(cell17);
        myTable.addCell(cell18);
        document.add(myTable);

    }

}
