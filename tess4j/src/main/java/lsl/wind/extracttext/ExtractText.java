package lsl.wind.extracttext;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 处理图片中文字的通用流程，读取文件→处理→导出结果
 * @author lsl
 */
public abstract class ExtractText {
    /**
     * 给定目录读取所有文件路径，递归读取
      * @param dir 文件目录
     * @return 目录下所有文件
     */
//   public String[] getFilePath(String dir)throws FileNotFoundException {
//       //判空 ,apache.common
//
//       File dirFile=new File(dir);
//
////       if(!dirFile.isDirectory()) throw new FileNotFoundException("文件未发现"+dir);
////
////       List<String> res=new ArrayList<>();
////
////       //路径非目录路径，直接返回该文件
////       if(!dirFile.isDirectory()){
////           res.add(dirFile.getName());
////       }else {
////           //递归获取目录下文件
////           String[] tmp=dirFile.list();
////           List
////
////
////       }
//
//
//
//   }


}
