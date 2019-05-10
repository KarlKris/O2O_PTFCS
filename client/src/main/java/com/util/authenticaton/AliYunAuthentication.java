package com.util.authenticaton;

import com.Exception.CustomizeException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.model.AuthDO;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.codec.binary.Base64.encodeBase64;



/**
 * 使用APPCODE进行云市场ocr服务接口调用
 */

public class AliYunAuthentication {

    /*
     * 获取参数的json对象
     */
    public static JSONObject getParam(int type, String dataValue) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("dataType", type);
            obj.put("dataValue", dataValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Map auchenticatiion(String imgBase64, String side, AuthDO authDO) throws CustomizeException{
        Map map = new HashMap();
        String host = "http://dm-51.data.aliyun.com";
        String path = "/rest/160601/ocr/ocr_idcard.json";
        String appcode = "adf1eb19b9e344b6b0576b4aec21de95";
        //如果文档的输入中含有inputs字段，设置为True， 否则设置为False
        Boolean is_old_format = false;
        //请根据线上文档修改configure字段
        JSONObject configObj = new JSONObject();
        configObj.put("side", side);
        String config_str = configObj.toString();
        //            configObj.put("min_size", 5);
        //            String config_str = "";

        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        Map<String, String> querys = new HashMap<String, String>();

        // 拼装请求body的json字符串
        JSONObject requestObj = new JSONObject();
        try {
            if(is_old_format) {
                JSONObject obj = new JSONObject();
                obj.put("image", getParam(50, imgBase64));
                if(config_str.length() > 0) {
                    obj.put("configure", getParam(50, config_str));
                }
                JSONArray inputArray = new JSONArray();
                inputArray.add(obj);
                requestObj.put("inputs", inputArray);
            }else{
                requestObj.put("image", imgBase64);
                if(config_str.length() > 0) {
                    requestObj.put("configure", config_str);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String bodys = requestObj.toString();

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            int stat = response.getStatusLine().getStatusCode();
            if(stat != 200){
                System.out.println("Http code: " + stat);
                System.out.println("http header error msg: "+ response.getFirstHeader("X-Ca-Error-Message"));
                System.out.println("Http body error msg:" + EntityUtils.toString(response.getEntity()));
                String msg = side.equals("face") ? "身份证正面照不合规范！" : "身份证反面照不合规范";
                map.put("status",false);
                map.put("msg",msg);
                return map;
            }

            String res = EntityUtils.toString(response.getEntity());
            //返回结果
            JSONObject res_obj = JSON.parseObject(res);
            if(is_old_format) {
                JSONArray outputArray = res_obj.getJSONArray("outputs");
                String output = outputArray.getJSONObject(0).getJSONObject("outputValue").getString("dataValue");
                JSONObject out = JSON.parseObject(output);
                System.out.println("============================================================================");
                System.out.println(out.toJSONString());
            }else{
                //构建AuthDO
                BuildAuthDOUtil.build(res_obj,authDO,side);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status",true);
        return map;
    }

}