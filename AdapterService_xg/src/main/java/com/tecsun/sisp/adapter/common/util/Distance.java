package com.tecsun.sisp.adapter.common.util;

import com.tecsun.sisp.adapter.common.https.HttpClientUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xumaohao on 2017/9/20.
 */
public class Distance {
    //地球平均半径
    private static final double EARTH_RADIUS = 6378137;
    //把经纬度转为度（°）
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    /**
     * 根据地址计算经纬度 出参为： 经度,纬度
     * @param address
     * @return
     */
    public static String getCoord(String address){
        //配置您申请的 KEY
        String APPKEY = com.xx.util.property.Config.getInstance().get("atlas.coord");
        try{String result =null;
            String url ="http://restapi.amap.com/v3/geocode/geo";//请求接口地址
            Map params = new HashMap();//请求参数
            params.put("key",APPKEY);//应用 APPKEY(应用详细页查询)
            params.put("address",address);//返回数据的格式,xml 或 json，
            params.put("output","json");//返回数据的格式,xml 或 json，
            Map<String,Object> map=new HashMap<String, Object>();

            result =Distance.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("status") ==1){
                List geocodes = (List) object.get("geocodes");
                String coord = JSONObject.fromObject(geocodes.get(0)).get("location").toString();
                return coord;
            }else{
                System.out.println(object.get("infocode") + ":" + object.get("info"));
            }
        }catch(Exception e){
//            logger.error("转换经纬度失败", e);
        }
        return "";
    }

    /**
     * 本地计算
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     * origin，destination的格式为：   经度,纬度
     * @return
     */
    public static double getDistanceByLocal(String origin, String destination) {
        if (StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination)) {
            return -1;
        }
        double lng1 = Double.parseDouble(origin.split(",")[0]);
        double lat1 = Double.parseDouble(origin.split(",")[1]);
        double lng2 = Double.parseDouble(destination.split(",")[0]);
        double lat2 = Double.parseDouble(destination.split(",")[1]);
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(
                Math.sqrt(
                        Math.pow(Math.sin(a / 2), 2)
                                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)
                )
        );
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }


    /**
     * 根据高德地图计算步行距离，结果可能比直接计算两点记录大
     * @param origin
     * @param destination
     * @return 成功则返回距离 失败返回-1
     */
    public static double getDistanceByPlat(String origin, String destination){
        //配置您申请的 KEY
        String APPKEY = com.xx.util.property.Config.getInstance().get("deviceRegist_atlas_coord");
        try{
            String result =null;
            String url ="http://restapi.amap.com/v3/direction/walking";//请求接口地址
            Map params = new HashMap();//请求参数
            params.put("key",APPKEY);//应用 APPKEY(应用详细页查询)
            params.put("origin",origin);//返回数据的格式,xml 或 json，
            params.put("destination",destination);//返回数据的格式,xml 或 json，
            params.put("output","json");//返回数据的格式,xml 或 json，
            Map<String,Object> map=new HashMap<String, Object>();

            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("status") ==1){
                double distance = Double.parseDouble(JSONObject.fromObject(((List) JSONObject.fromObject(object.get("route")).get("paths")).get(0)).get("distance").toString());
                return distance;
            }else{
//                logger.error(object.get("infocode") + ":" + object.get("info"));
            }
        }catch(Exception e){
//            logger.error("计算距离失败", e);
        }
        return -1;
    }

    /**
     * *
     @param strUrl 请求地址
      * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        String rs = "";
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            rs = HttpClientUtil.getData(strUrl, null, false, false, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //将 map 型转为请求参数型
    public static String urlencode(Map<String,Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
//                logger.error("将map型转为请求参数型失败", e);
            }
        }
        return sb.toString();
    }
}
