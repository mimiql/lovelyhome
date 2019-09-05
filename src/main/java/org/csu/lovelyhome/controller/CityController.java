package org.csu.lovelyhome.controller;

import org.csu.lovelyhome.base.BaseController;
import org.csu.lovelyhome.base.Response;
import org.csu.lovelyhome.pojo.vo.CityVO;
import org.csu.lovelyhome.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController extends BaseController {

    @Autowired
    private CityService cityService;

    @GetMapping("/province")
    public Response getProvince(){
        List<CityVO> cityVOS = cityService.getProvince();
        if (cityVOS.size() == 0){
            return fail("未获取到城市");
        }
        return success(cityVOS);
    }

    @GetMapping("/city/{proNO}")
    public Response getCity(@PathVariable int proNO){
        List<CityVO> cityVOS = cityService.getCity(proNO);
        if (cityVOS == null){
            return fail("未获取到城市");
        }
        return success(cityVOS);
    }

    @GetMapping("/county/{proNO}/{cityNO}")
    public Response getCounty(@PathVariable int proNO,@PathVariable int cityNO){
        List<CityVO> cityVOS = cityService.getCounty(proNO,cityNO);
        if (cityVOS == null){
            return fail("未获取到城市");
        }
        return success(cityVOS);
    }

}
