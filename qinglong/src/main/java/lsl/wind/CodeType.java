package lsl.wind;

/**
 * @date 2020/10/21
 * @author lsl
 * 编码类型
 */
public enum CodeType {
    BASE64("base64");
    private String name;

    //枚举思想，不能使用public声明构造方法
    private CodeType(String name){
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
