package lsl.wind.pdftool;

import org.junit.Test;

import java.io.File;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @Author lsl
 * @Date 2020/11/9 10:51
 */

public class Image2TextTest {

    @Test
    public void imgDirToText() {
        Comparator comparator=new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                String a1=o1.getAbsolutePath();
                String a2=o2.getAbsolutePath();
                a1=a1.substring(a1.indexOf('_')+1,a1.indexOf('.'));
                a2=a2.substring(a2.indexOf('_')+1,a2.indexOf('.'));
                return Integer.parseInt(a1)-Integer.parseInt(a2);
            }
        };
        Image2Text.ImgDirToText("E:\\新建文件夹","E:\\ctf特训营1.txt",comparator);
        //Image2Text.ImgDirToText("E:\\CTF特训营","E:\\ctf特训营2.txt",comparator);
    }


    @Test
    public void getAuth(){
        System.out.println(Image2Text.getAuth());
    }

}
