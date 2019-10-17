package com.njcb;


import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.IOException;


public class ReadPdfContent {

    static  String PDFPath = "template/A78021.pdf";

    public static void main(String[] args) throws IOException {

        PdfReader reader =  new PdfReader(PDFPath);
        String pageContent ="";
        int pageNum = reader.getNumberOfPages();   //获取pdf页数
        for(int i=1;i<=pageNum;i++){
            pageContent += PdfTextExtractor.getTextFromPage(reader, i);//读取第i页的文档内容
        }


        System.out.println(pageNum);
        System.out.println(pageContent);


    }



}
