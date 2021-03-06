package com.tecsun.sisp.fakamanagement.modules.util;

import java.io.IOException;

import com.tecsun.sisp.fakamanagement.common.JsonMapper;
import com.tecsun.sisp.fakamanagement.common.Result;

/**全局消息处理类，系统逻辑
 * ClassName: GlobalResult
 * Description:
 * Author： 张清洁
 * CreateTime： 2015年06月12日 16时:57分
 */
public class GlobalResult {

    public static String error_301(String message , Object obj){
        return globalResult("301",message,obj);
    }

    public static String error_302(String message){
        return globalResult("302",message==null?"CMP-EERR-302:redis服务器读取数据出错":message,null);
    }

    public static String error_303(String message){
        return globalResult("303",message==null?"IFAC-EERR-303:拦截非法访问德生宝":message,null);
    }

    private static String globalResult(String code,String message,Object obj){
        Result result = new Result(code,message, obj);
        try {
            return JsonMapper.buildNormalMapper().toJson(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
