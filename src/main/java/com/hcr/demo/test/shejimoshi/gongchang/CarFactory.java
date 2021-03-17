package com.hcr.demo.test.shejimoshi.gongchang;

import org.springframework.util.StringUtils;

/**
 * @author Administrator
 */
public class CarFactory {

    public Car getCar(String carType){
        if(StringUtils.isEmpty(carType)){
            return null;
        }
        if("ZXC".equals(carType)){
            return new zixingche();
        }
        if("KC".equals(carType)){
            return new KaChe();
        }
        if("JC".equals(carType)){
            return new jiaoche();
        }
        return null;
    }
}
