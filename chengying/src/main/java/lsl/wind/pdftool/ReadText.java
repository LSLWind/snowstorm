package lsl.wind.pdftool;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author lsl
 * @Date 2020/11/6 15:28
 * 从pdf文件中读取文本
 */
public class ReadText {
    /**
     * 指定pdf与目标文件路径，从pdf文件读取文本到指定文件
     * @param pdfPath pdf文件路径，绝对路径
     * @param targetPath 目标文件，绝对路径
     */
    public static void readTextFromPdf(String pdfPath,String targetPath)  {
        //pdf文件校验
        File pdfFile=new File(pdfPath);
        if(!pdfFile.exists()){
            System.out.println("pdf文件未发现："+pdfPath);
            return;
        }


        File targetFile=new File(targetPath);

        PDDocument pdDocument=null;

        try{
            //读取文档
            pdDocument=PDDocument.load(pdfFile);

            //获取文档页码
            int pages=pdDocument.getNumberOfPages();

            //读取文档内容并设置读取参数
            PDFTextStripper stripper=new PDFTextStripper();
            stripper.setSortByPosition(true);
            stripper.setStartPage(1);
            stripper.setEndPage(pages);
            String content=stripper.getText(pdDocument);

            //校验目标文件
            if(targetFile.exists()){
               System.out.println("文件已存在，将覆盖文件");
            }else {
                System.out.println("目标文件不存在，新建文件");
                targetFile.createNewFile();
            }

            //写入目标文件
            FileWriter fileWriter=new FileWriter(targetFile);
            fileWriter.write(content);

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("写入文件成功");
    }



    /**
     * PDF文件转PNG/JPEG图片
     * @param PdfFilePath 完整路径
     * @param dstImgFolder 图片存放的文件夹
     * @param dpi dpi越大转换后越清晰，相对转换速度越慢,一般电脑默认96dpi
     */
    public static void pdf2Image(String PdfFilePath,
                                     String dstImgFolder,
                                     int dpi) {
        File file = new File(PdfFilePath);
        PDDocument pdDocument;
        try {
            //获取pdf文件名称与上层路径
            String imgPDFPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            // 获取图片文件名
            String imagePDFName = file.getName().substring(0, dot);

            String imgFolderPath = null;
            if (dstImgFolder.equals("")) {
                // 获取图片存放的文件夹路径
                imgFolderPath = imgPDFPath + File.separator + imagePDFName;
            } else {
                imgFolderPath = dstImgFolder + File.separator + imagePDFName;
            }

            if (createDirectory(imgFolderPath)) {
                pdDocument = PDDocument.load(file);
                PDFRenderer renderer = new PDFRenderer(pdDocument);
                int pages =pdDocument.getNumberOfPages();// 获取PDF页数
                System.out.println("PDF page number is:" + pages);
                StringBuffer imgFilePath = null;
                for (int i = 0; i < pages; i++) {
                    String imgFilePathPrefix = imgFolderPath
                            + File.separator + imagePDFName;
                    imgFilePath = new StringBuffer();
                    imgFilePath.append(imgFilePathPrefix);
                    imgFilePath.append("_");
                    imgFilePath.append(String.valueOf(i + 1));
                    imgFilePath.append(".png");// PNG
                    File dstFile = new File(imgFilePath.toString());
                    BufferedImage image = renderer.renderImageWithDPI(i, dpi);
                    ImageIO.write(image, "png", dstFile);// PNG
                }
                System.out.println("PDF文档转PNG图片成功！");
            } else {
                System.out.println("PDF文档转PNG图片失败："
                        + "创建" + imgFolderPath + "失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean createDirectory(String folder) {
        File dir = new File(folder);
        if (dir.exists()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }

}
