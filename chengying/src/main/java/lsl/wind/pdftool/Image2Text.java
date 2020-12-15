package lsl.wind.pdftool;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.util.Base64Util;
import lsl.wind.pdftool.utils.FileUtil;
import lsl.wind.pdftool.utils.HttpUtil;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


/**
 * 图片转文字，使用百度API，文字识别SDK
 *
 * @Author lsl
 * @Date 2020/11/9 10:20
 */
public class Image2Text extends ApiOcrClient {


    /**
     * 将指定图片目录的所有图片文件转换成文本到指定文本文件
     *
     * @param imgDirPath 图片目录，按顺序识别所有图片
     * @param textPath   目标文本文件路径
     * @param comparator 比较器，目录下图片的排序规则
     */
    public static void ImgDirToText(String imgDirPath, String textPath, Comparator<File> comparator) {
        //图片目录校验
        File imgDir = new File(imgDirPath);
        if (!imgDir.exists() || !imgDir.isDirectory()) {
            System.out.println("错误：图片目录不存在或是文件");
            return;
        }

        //目标文本文件
        File textFile = new File(textPath);

        try {
            if (!textFile.exists() && !textFile.createNewFile()) {
                System.out.println("创建目标文件失败");
                return;
            }
            FileWriter fileWriter = new FileWriter(textFile);
            //遍历图片目录，按照顺序规则严格指定
            File[] imgFiles = imgDir.listFiles();
            //指定排序规则
            Arrays.sort(imgFiles, comparator);

            //获取Token
            String accessToken=getAuth();

            for (File file : imgFiles) {
                System.out.println("开始将图片转换为文本：" + file.getAbsolutePath());
                //获取OCR识别结果
                String res = baiduAiOcrGeneralBasic(file.getAbsolutePath(),GENERAL_BASIC_URL,accessToken);
                //写入文件
                fileWriter.write(res);
            }

            //关闭文件
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 百度AI OCR文字识别，注意每天访问次数与accessToken过期时间
     * @param imgPath 本地图片路径
     * @param url 百度API URL
     * @param accessToken 百度API 访问Token
     * @return 识别结果
     */
    public static String baiduAiOcrGeneralBasic(String imgPath,String url,String accessToken) {

        try {
            // 本地文件路径
            byte[] imgData = FileUtil.readFileByBytes(imgPath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;
            //发起请求
            String result = HttpUtil.post(url, accessToken, param);

            //JSON转换
            ApiOcrRes apiOcrRes = JSON.parseObject(result, ApiOcrRes.class);

            StringBuilder res=new StringBuilder();
            System.out.println("识别结果行数："+apiOcrRes.getWordsResultNum());
            if(apiOcrRes.getWordsResult()!=null){
                for (WordResult words : apiOcrRes.getWordsResult()) {
                    res.append(words.getWords()).append("\n");
                }
            }
            return res.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 将指定图片文件转换成文本并返回字符串
     * 带位置高精度识别，访问次数500/天
     *
     * @param imgPath 图片文件
     * @return 识别结果
     */
    public static String ImgToText(String imgPath) {
        if (imgPath == null || imgPath.length() == 0 || !new File(imgPath).exists() || !new File(imgPath).isFile()) {
            System.out.println("文件不存在");
            return "";
        }

        //返回结果
        StringBuilder res = new StringBuilder();

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("detect_direction", "true");

        //ocr识别 参数为本地图片路径 获取OCR识别结果
        JSONObject resOcr = client.basicAccurateGeneral(imgPath, options);
        //JSON转换
        ApiOcrRes apiOcrRes = JSON.parseObject(resOcr.toString(), ApiOcrRes.class);

        System.out.println("识别结果："+apiOcrRes.getWordsResultNum());
        if(apiOcrRes.getWordsResult()!=null){
            for (WordResult words : apiOcrRes.getWordsResult()) {
                res.append(words.getWords()).append("\n");
            }
        }
        return res.toString();
    }

}

