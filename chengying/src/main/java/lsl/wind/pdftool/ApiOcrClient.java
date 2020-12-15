package lsl.wind.pdftool;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @Author lsl
 * @Date 2020/12/15 9:00
 *
 * 百度API OCR 文字识别客户端
 * 用于配置权鉴认证
 */
public abstract class ApiOcrClient {
    //设置APPID/AK/SK
    public static final String APP_ID = "22949594";
    // 官网获取的 API Key 更新为你注册的
    public static final String API_KEY = "i7rOgouyHRSxzV1avtbfmMiF";
    // 官网获取的 Secret Key 更新为你注册的
    public static final String SECRET_KEY = "VaM5TfGn9CMp2KEG8auxgnrFi9sZDaiv";

    //标准版通用文字识别，50000次/天
    public static final String GENERAL_BASIC_URL="https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";

    /**
     * 子类可使用的客户端，用于连接
     */
    protected static AipOcr client=new AipOcr(APP_ID, API_KEY, SECRET_KEY);

    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        return getAuth(API_KEY, SECRET_KEY);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result.toString());
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            System.err.print("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }
}