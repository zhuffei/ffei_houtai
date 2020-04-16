package zhuffei.ffei.Tool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuffei
 * date 2020.3.15
 */
public class Return {

    public static Map ok(String msg,Object object) {
        Map map = new HashMap();
        map.put("state",true);
        map.put("msg",msg);
        map.put("data",object);
        return map;
    }
    public static Map ok() {
        Map map = new HashMap();
        map.put("state",true);
        return map;
    }
    public static Map ok(Object object) {
        Map map = new HashMap();
        map.put("state",true);
        map.put("data",object);
        return map;
    }
    public static Map error(){
        Map map = new HashMap();
        map.put("msg",false);
        return map;
    }
    public static Map error(String msg){
        Map map = new HashMap();
        map.put("state",false);
        map.put("msg",msg);
        return map;
    }

    public static Map ret(String msg){
        Map map = new HashMap();
        map.put("msg",msg);
        return map;
    }
}
