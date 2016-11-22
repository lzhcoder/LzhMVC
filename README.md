#LzhMVC,一款仿SpringMVC框架的轻便快捷的Java MVC开发框架
 
 
@Controller
public class test {


    /**
     * @author gaorui
     * @param s1
     * @param s2
     * @param request
     * @param response
     * @param session
     * 普通url处理
     */
    @MapURL(value = "get",RequestMethod = RequestMethod.GET)
    public void get(String s1,String s2,HttpServletRequest request, HttpServletResponse response, HttpSession session){

            return;
    }


    /**
     * @author gaorui
     * @return String
     * 服务端跳转页面处理,默认 web-inf下所有 .html文件
     */
    @MapURL(value="foward")
    public String foward(){

        return "page/succ";

    }

    /**
     * @author gaorui
     * @param userid
     * @return Object
     * 服务端处理前端ajax请求,返回json数据
     */
    @MapURL(value = "getUser", RequestMethod = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUser(int userid){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid",userid);
        return jsonObject;

    }
}

```
####mvc框架功能介绍
* 注解实现
* @MapURL注解实现http请求路由
* 反射实现方法参数注入
* String返回类型方法转发请求
* @ResponseBody注解实现ajax接口
* 增加config.ini配置文件,实现定向动态扫描项目中的@Controller 类
* 增加注解参数RequestMethod 默认http请求类型,请求类型不合法返回405状态码

####commit log
* 1.0 初始化项目
* 1.1 dhy join 
* 1.2 mvc框架功能雏形
* 1.3 增加handlerMapping处理器映射,控制器卸耦
* 1.4 增加config.ini配置文件,实现定向动态扫描项目中的@Controller 类
* 1.5 增加注解参数RequestMethod 默认http请求类型,请求类型不合法返回405状态码
* 1.6 下一步准备做项目的容错处理,过滤器准备用原生的不准备加入框架
* 1.7 修改部分目录结构,画框架整体流程图,后面继续做容错处理和性能优化