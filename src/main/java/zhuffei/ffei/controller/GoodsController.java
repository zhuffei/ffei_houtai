package zhuffei.ffei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zhuffei.ffei.Tool.Return;
import zhuffei.ffei.entity.Goods;
import zhuffei.ffei.service.IGoodsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/9 15:17
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 分页查询商品，按时间排序
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("listRecentGoods")
    public Map listRecentGoods(HttpServletRequest request){     //必须传入每页的条数和页数
//        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
//        Integer pageNumber = Integer.valueOf(request.getParameter("pageNumber"));

        Integer pageSize = 1;
        Integer pageNumber = 1;
        List<Goods> data = goodsService.listRecentGoods(pageSize,pageNumber);
        System.out.println(data);
        return  Return.ok("",data);
    }
}
