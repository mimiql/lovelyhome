package org.csu.lovelyhome.common.util;

import net.sf.json.JSONObject;
import org.csu.lovelyhome.pojo.vo.PointVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class CoordinateUtil {
    private static final String AK = "IYvMboCMG9Kx5dDfQO9Mnxpad50Tz984";

    public static PointVO getCoordinate(String address){
        if (address != null && !"".equals(address)) {
            address = address.replaceAll("\\s*", "").replace("#", "栋");
            String url = "http://api.map.baidu.com/place/v2/search?ak=" + AK + "&output=json&query=" + address + "&page_size=1&page_num=0&scope=1&region=全国";
            String json = loadJSON(url);
            if (json != null && !"".equals(json)) {
                JSONObject obj = JSONObject.fromObject(json);
                if ("0".equals(obj.getString("status"))) {
                    double lng = obj.getJSONArray("results").getJSONObject(0).getJSONObject("location").getDouble("lng"); // 经度
                    double lat = obj.getJSONArray("results").getJSONObject(0).getJSONObject("location").getDouble("lat"); // 纬度
                    DecimalFormat df = new DecimalFormat("#.######");
                    PointVO pointVO = new PointVO();
                    pointVO.setLongitude(Double.valueOf(df.format(lng)));
                    pointVO.setLatitude(Double.valueOf(df.format(lat)));
                    return pointVO;
                }
            }
        }
        return null;
    }

    private static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL url1 = new URL(url);
            URLConnection yc = url1.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
        return json.toString();
    }
}
