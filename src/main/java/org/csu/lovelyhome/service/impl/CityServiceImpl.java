package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.common.util.CityUtil;
import org.csu.lovelyhome.pojo.vo.CityVO;
import org.csu.lovelyhome.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Override
    public List<CityVO> getProvince() {
        String str = CityUtil.getCity(CityUtil.getBase());
        return CityVO.fromStr(str);
    }

    @Override
    public List<CityVO> getCity(int proNO) {
        String url = CityUtil.getBase() + "/" + String.valueOf(proNO);
        String str = CityUtil.getCity(url);
        return CityVO.fromStr(str);
    }

    @Override
    public List<CityVO> getCounty(int proNO, int cityNO) {
        String url = CityUtil.getBase() + "/" + String.valueOf(proNO) + "/" + String.valueOf(cityNO);
        String str = CityUtil.getCity(url);
        return CityVO.fromStr(str);
    }
}
