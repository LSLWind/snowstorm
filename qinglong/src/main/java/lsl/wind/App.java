package lsl.wind;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println(EncodeUtil.base64Encode("DajiDali_JinwanChiji"));
        Scanner scanner=new Scanner(System.in);
        String str=scanner.next();

        String[] ss=DetectUtil.detectEncodeType(str);
        for(String sss:ss){
            if(sss.equals(CodeType.BASE64.getName())){
                System.out.println(DecodeUtil.decodeBase64(str));
            }
        }
    }
}
