package lsl.wind;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @date 2020/10/21
 * @author lsl
 * 侦测工具
 */
public class DetectUtil {
    /**
     * 检测字符串是否可能被编码
     * @param str 要检查的字符串
     * @return 如果被编码，返回可能的编码类型，否则返回空数组
     */
   public static String[] detectEncodeType(String str){
       if(str==null||str.length()<=0)return new String[0];

       List<String> res=new ArrayList<>();
       //去掉首尾空格
       str=str.trim();

       //尝试检测
       if(isBase64Encode(str)){
           res.add(CodeType.BASE64.getName());
       }


       return res.toArray(new String[res.size()]);
    }

    /**
     * 使用正则表达式判断是否符合base64编码条件
     * @param str 要检查的字符串
     * @return 是否符合base64编码后的格式
     */
    public static boolean isBase64Encode(String str){
        //匹配base64编码后格式的正则表达式
       String matchRegx="^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";

        //开始匹配,返回结果
        return Pattern.matches(matchRegx,str);
    }
}
