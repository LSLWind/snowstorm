package lsl.wind.pdftool;

import org.junit.Test;


/**
 * @Author lsl
 * @Date 2020/11/6 15:48
 */
public class ReadTextTest {

    @Test
    public void readTextFromPdf() {
        ReadText.readTextFromPdf("I:\\pdf\\java 8实战.pdf","E:\\cccc.txt");
    }

    @Test
    public void pdf2Image(){
        ReadText.pdf2Image("E:\\CTF特训营.pdf","E:\\",96);
    }
}
