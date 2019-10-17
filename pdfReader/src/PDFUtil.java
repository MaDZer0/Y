import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.tools.PDFText2HTML;

public class PDFUtil {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PDFUtil pu = new PDFUtil();
		pu.readPDF("template/A78021.pdf");
	}
	
    /**  
     * 读PDF文件，使用了pdfbox开源项目  
     * @param fileName  
     */  
    public void readPDF(String fileName) {
        FileInputStream in = null;
        RandomAccessRead ra = null;
        try {   
            in = new FileInputStream(fileName);
            ra = new RandomAccessBufferedFileInputStream(in);
            // 新建一个PDF解析器对象   
            PDFParser parser = new PDFParser(ra);
            // 对PDF文件进行解析   
            parser.parse();   
            // 获取解析后得到的PDF文档对象   
            PDDocument pdfDoc = parser.getPDDocument();
            PDFText2HTML pdf_t2h = new PDFText2HTML();
            String text = pdf_t2h.getText(pdfDoc);
            //System.out.println(text);
            
            StringWriter writer = new StringWriter();
            pdf_t2h.writeText(pdfDoc, writer);
            
            System.out.println(">>>>>> PDF文件的文本内容如下 <<<<<<");
            System.out.println(writer.toString());


        } catch (Exception e) {
            e.printStackTrace();   
        } finally {

            if (in != null || ra != null) {
                try {   
                    in.close();
                    ra.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }   
            }   
        }   
    }   
}
