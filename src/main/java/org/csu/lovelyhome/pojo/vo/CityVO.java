package org.csu.lovelyhome.pojo.vo;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CityVO {
    private Integer cityNO;
    private String CityName;

    public static List<CityVO> fromStr(String str){
        List<CityVO> cityVOS = new ArrayList<>();
        if (StringUtils.isEmpty(str) || str.equals("[]")){
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(str );
        if(jsonArray.size() > 0){
            for (int i = 0 ; i < jsonArray.size() ; ++i){
                CityVO cityVO = new CityVO();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                cityVO.setCityName(jsonObject.get("name").toString());
                cityVO.setCityNO(Integer.parseInt(jsonObject.get("id").toString()));
                cityVOS.add(cityVO);
            }
        }
        return cityVOS;
    }

    public Integer getCityNO() {
        return cityNO;
    }

    public void setCityNO(Integer cityNO) {
        this.cityNO = cityNO;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }
}
