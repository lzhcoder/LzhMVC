package org.aisframework.web.param;

import org.aisframework.web.structure.MethodPro;
import org.aisframework.web.test.test;
import org.aisframework.web.utils.CollectionUtils;
import org.aisframework.web.utils.ReflectProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by gaorui on 16/6/19.
 */
public class HandlerMapping {

    public static void  HandlerMapping(HttpServletRequest req,HttpServletResponse resp, Map<String,MethodPro> methodProMap, String key) {

        try {
        //参数名称列表
        List<String> paramlist = MethodResolver.getMethodNames(methodProMap.get(key).getMethod().getDeclaringClass().getName(), key);
        //参数值
        Map params = req.getParameterMap();
        //根据访问路径唯一的key获得访问方法的详细信息
        MethodPro methodPro = methodProMap.get(key);
        Method method = methodPro.getMethod();
        List<String> classNames = CollectionUtils.classArrToStringList(method.getParameterTypes());
        //填充参数
        Object[] invokeParamVulue = MethodResolver.paramarray(paramlist, classNames, req, resp, params);;
        Method urlmethod = methodProMap.get(key).getMethod();

            String reqtype = req.getMethod().toUpperCase();

            //http请求处理
            if(methodPro.getUrlStyle().equals("POST")){
                    if(!reqtype.equals("POST")) {

                        resp.sendError(405);
                        //resp.getWriter().print("405 not allowed");
                        return;
                    }
            }

            //String返回方法参数类型处理
            if (urlmethod.getReturnType().getName().equals("java.lang.String")) {

                String uri = ReflectProcessor.parseMethod(test.class, key, invokeParamVulue).toString();
                req.getRequestDispatcher("WEB-INF/" + uri + ".html").forward(req, resp);
                return;

            //ajax接口处理
            } else if (methodProMap.get(key).getAjax()) {

                Object o = ReflectProcessor.parseMethod(test.class, key, invokeParamVulue);
                resp.getWriter().print(o);
                return;
            }

            ReflectProcessor.parseMethod(test.class, key, invokeParamVulue);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
