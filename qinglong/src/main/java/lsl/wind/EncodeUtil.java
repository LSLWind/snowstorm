package lsl.wind;

import com.sun.org.apache.xalan.internal.xsltc.dom.AdaptiveResultTreeImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2020/10/21
 * @author lsl
 * 编码工具
 */
public class EncodeUtil {
    /**
     * base64编码
     * @param str 要编码的字符串
     * @return base64编码号的字符串
     */
    public static String base64Encode(String str){
        StringBuilder sb=new StringBuilder(str);
        //1.不是3的倍数补充0x00进入
        int count0=3-str.length()%3;//计算需要补充的0的个数
        for(int i=0;i<count0;i++){
            sb.append((char)0x00);
        }
        //2.以24bit为分组按照6bit化成4个小组，6bit高位补0
        List<Integer> index=new ArrayList<>();
        for(int i=0;i<sb.length();i+=3){
            index.add(((int)sb.charAt(i)& 0xFC)>>2);
            index.add(((((int)sb.charAt(i) & 0x03) << 4) + (((int)sb.charAt(i+1) & 0xF0) >> 4)));
            index.add((((int)sb.charAt(i+1) & 0x0F) << 2) + (((int)sb.charAt(i+2)& 0xC0) >> 6));
            index.add(((int)sb.charAt(i+2) & 0x3F));
        }
        //3.按照base64表将索引映射为字符
        StringBuilder res=new StringBuilder();
        for(int i:index){
            if(0<=i && i<=25){
                res.append((char)('A' +i));
            }else if(26<=i && i<=51){
                res.append((char)('a'+i-26));
            }else if(52<=i && i<=61){
                res.append((char)('0'+i-52));
            }else if(i==62){
                res.append('+');
            }else if(i==63){
                res.append('/');
            }
        }
        //4.将末尾补全的0x00变为=
        res=new StringBuilder(res.substring(0,res.length()-count0));
        for(int i=0;i<count0;i++){
            res.append('=');
        }

        return res.toString();
    }
}
