package lsl.wind;

import java.util.Scanner;

/**
 * @Author lsl
 * @Date 2020/10/29 17:56
 */
public class GetterSetterGenerator {
    private static String PUBLIC="public";
    private static String Get="get";
    private static String Set="set";
    private static String BLOCK=" ";
    private static String LEFT_K="(";
    private static String RIGHT_K=")";
    private static String LEFT_D="{";
    private static String RIGHT_D="}";
    private static String LINE="\n";
    private static String THIS="this";
    private static String RETURN="return";
    private static String END=";";
    private static String TAB="    ";
    private static String DOG=".";
    private static String EQUAL="=";
    private static String VOID="void";

    /**
     * getter与setter生成，每一行需遵循固定格式
     * 例：private long id;
     * @param oneLine 一行数据
     * @return 生成方法字符串
     */
    public static String getterAndSetterGenerator(String oneLine){
        if(oneLine==null||oneLine.equals(""))return "";
        //解析格式
        oneLine=oneLine.trim();
        String[] tags=oneLine.split(" ");
        tags[2]=tags[2].substring(0,tags[2].length()-1);//去掉末尾的;
        //生成Getter
        return PUBLIC + BLOCK + tags[1] + BLOCK +
                Get + CharacterTool.convertToUpCharacter(tags[2].charAt(0)) + tags[2].substring(1) +
                LEFT_K + RIGHT_K + BLOCK + LEFT_D + LINE +
                TAB + RETURN + BLOCK + tags[2] + END + LINE +
                RIGHT_D + LINE +
                //生成Setter
                PUBLIC + BLOCK + VOID + BLOCK +
                Set + CharacterTool.convertToUpCharacter(tags[2].charAt(0)) + tags[2].substring(1) +
                LEFT_K + tags[1] + BLOCK + tags[2] + RIGHT_K + BLOCK + LEFT_D + LINE +
                TAB + THIS + DOG + tags[2] + EQUAL + tags[2] + END + LINE +
                RIGHT_D + LINE;
    }
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String str=scanner.nextLine();
            System.out.println(getterAndSetterGenerator(str));
        }

    }
}
