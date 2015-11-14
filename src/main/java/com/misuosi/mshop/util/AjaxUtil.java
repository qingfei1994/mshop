package com.misuosi.mshop.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Description		: Ajax工具类
 * <p/>
 * <br><br>Time		: 2014-8-29  上午9:34:26
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
public class AjaxUtil {

    private static Logger log = LoggerFactory.getLogger(AjaxUtil.class);

    /**
     * ajax发送返回信息
     *
     * @param data
     */
    public static void sendBack(HttpServletResponse res, String data) {
        PrintWriter out = null;
        res.setContentType("text/html;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        try {
            out = res.getWriter();
            if (out != null) {
                out.print(data);
            }
        } catch (Exception e) {
            log.error("ajax发生异常:" + e.getMessage());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

    }

    /**
     * ajax发送返回信息
     *
     * @param object
     */
    public static void sendBackObject(HttpServletResponse res, Object object) {
        String data = JSON.toJSONString(object);
        PrintWriter out = null;
        res.setContentType("application/json;charset=utf-8");
        res.setCharacterEncoding("UTF-8");
        try {
            out = res.getWriter();
            if (out != null) {
                out.print(data);
            }
        } catch (Exception e) {
            log.error("ajax发生异常:" + e.getMessage());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

    }

}
