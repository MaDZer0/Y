package com.njcb;
import java.io.File;
import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;



public class CreateImage {

    public String fillTemplate(String templatePath,Map<String,String> info) throws Exception {

        // 生成的新文件路径
        Date dt = new Date();
        String newPDFPath = "/Users/zhongjie/Report/"+new java.text.SimpleDateFormat("yyyyMMdd").format(dt);
        if(!(new File(newPDFPath)).exists()){
            new File(newPDFPath).mkdirs();
        }
        String newPDFfile = newPDFPath+"/"+new Random().nextInt( 1000 )+"pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;

            out = new FileOutputStream(newPDFfile);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            try{
            AcroFields form = stamper.getAcroFields();


                BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

                for (Iterator it = form.getFields().keySet().iterator(); it.hasNext();) {
                String key=(String) it.next();
                if(info.containsKey(key)){
                    form.setFieldProperty(key, "textfont", bf, null);
                    form.setField(key,info.get(key) );
                }
            }

            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            //PDFImageWriter imageWriter = new PDFImageWriter();
            //doc.close();
            copy.close();
            reader.close();
           // b

        } catch (IOException e) {
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            stamper.close();

        }



        return  newPDFfile;

    }


}
