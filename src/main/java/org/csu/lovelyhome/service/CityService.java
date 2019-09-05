package org.csu.lovelyhome.service;

import org.csu.lovelyhome.pojo.vo.CityVO;

import java.util.List;

public interface CityService {

    List<CityVO> getProvince();

    List<CityVO> getCity(int proNO);

    List<CityVO> getCounty(int proNO,int cityNO);
}
