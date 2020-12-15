package lsl.wind.pdftool;

import java.util.List;

/**
 * @Author lsl
 * @Date 2020/11/9 13:49
 *
 * 百度API文字识别返回响应，返回数据以行为单位
 */
public class ApiOcrRes {
    private String logId;
    /** 返回结果集，wordResult代表一行 */
    private List<WordResult> wordsResult;
    private int wordsResultNum;

    public String getLogId() {
        return logId;
    }
    public void setLogId(String logId) {
        this.logId=logId;
    }

    public List<WordResult> getWordsResult() {
        return wordsResult;
    }
    public void setWordsResult(List<WordResult> wordsResult) {
        this.wordsResult=wordsResult;
    }
    public int getWordsResultNum() {
        return wordsResultNum;
    }
    public void setWordsResultNum(int wordsResultNum) {
        this.wordsResultNum=wordsResultNum;
    }
}
/**
 * 每行识别结果
 */
class WordResult{
    private String words;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}