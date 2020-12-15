package lsl.wind;

/**
 * @date 2020/10/21
 * @author lsl
 * 解码工具
 */
public class DecodeUtil {
    /**
     * 以base64方式解码
     * @param str 字符串
     * @return 解码后的字符串
     */
    public static String decodeBase64(String str){

        //1.将str按照Base64表转换成Base64表的索引
        int[] index=new int[str.length()];

        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                index[i] = c - 'A';
            } else if (c >= 'a' && c <= 'z'){
                index[i] = c - 'a' + 26;
            } else if (c >= '0' && c <= '9') {
                index[i] = c - '0' + 52;
            } else if (c == '+') {
                index[i] = 62;
            } else if (c == '/') {
                index[i] = 63;
            } else {//处理字符串末尾是'='的情况
                index[i] = 0;
            }
        }

        //for(int i:index)System.out.print(i+" ");

        //2.按照索引表将8bit数据去掉高位0之后以6bit拼接成8bit组，以24bit为大组拼接，因此需三次
        StringBuilder res=new StringBuilder();
        //以24bit为大组,j为原始索引，3*8=4*6=24
        for(int j=0;j<index.length;j+=4){
            //左移2位去掉高位0，6位有效数据加上后2位有效数据得到8bit，&操作得到指定位结果，下述同理
            //需要注意位运算的优先级，最好将每一步位运算都用括号括起来，否则可能出错
            res.append((char)((index[j]<<2)+ ((index[j+1]&0xF0)>>4)));//res.append(" ");
            res.append((char)(((index[j+1]&0x0F)<<4)+((index[j+2]&0xFC)>>2)));//res.append(" ");
            res.append((char)(((index[j+2]&0x03)<<6)+(index[j+3])));//res.append(" ");
        }

        return res.toString();
    }
}

