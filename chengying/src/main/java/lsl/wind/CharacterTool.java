package lsl.wind;

/**
 * @Author lsl
 * @Date 2020/11/2 9:22
 *
 * 字符工具
 */
public class CharacterTool {
    /**
     * 将单个字符转换成大写，非字母返回原字符
     * @param c 要转换的字符
     * @return 对应的大写字符
     */
    public static char convertToUpCharacter(char c){
        if('a'<=c&&c<='z'){
            c-=32;
        }
        return c;
    }
}
