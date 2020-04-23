package zhuffei.ffei.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zhuffei.ffei.Tool.Return;
import zhuffei.ffei.entity.CommentUserVO;
import zhuffei.ffei.entity.Goods;
import zhuffei.ffei.entity.GoodsUserVO;
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
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("listRecentGoods")
    public Map listRecentGoods(@RequestBody Map<String, String> map) {     //必须传入每页的条数和页数
        Integer pageSize = Integer.valueOf(map.get("pageSize"));
        Integer pageNumber = Integer.valueOf(map.get("pageNumber"));
        if (null == pageSize || null == pageNumber) {
            System.out.println("网络异常");
            return Return.error("网络异常");
        }
        List<GoodsUserVO> data = goodsService.listRecentGoods(pageSize, pageNumber);
        return Return.ok("", data);
    }


    /**
     * 新增商品，接收商品信息和图片
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("add")
    public Map addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> imageNames = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        try {
            request.setCharacterEncoding("utf-8"); //设置编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取文件需要上传到的路径
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\goodsImg";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        factory.setRepository(new File(path));
        //设置 缓存的大小
        factory.setSizeThreshold(1024 * 1024);
        //文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //可以上传多个文件
            List<FileItem> list = (List<FileItem>) upload.parseRequest(new ServletRequestContext(request));
            for (FileItem item : list) {
                //获取属性名字
                String name = item.getFieldName();
                //如果获取的 表单信息是普通的 文本 信息
                if (item.isFormField()) {
                    //获取用户具体输入的字符串,因为表单提交过来的是 字符串类型的
                    String value = item.getString();
                    map.put(name, value);
                } else {
                    //获取路径名
                    String value = item.getName();
                    //索引到最后一个反斜杠
                    int start = value.lastIndexOf(".");
                    //截取 上传文件的 后缀
                    String fileType = value.substring(start);
                    request.setAttribute(name, fileType);
                    String fileName = UUID.randomUUID() + fileType;
                    //写到磁盘上
                    item.write(new File(path, fileName));//第三方提供的
                    imageNames.add(fileName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败");
            return Return.error();
        }
        Goods goods = new Goods();
        goods.setName(new String(map.get("title").getBytes("ISO8859-1"), "utf-8"));
        goods.setDes(new String(map.get("describe").getBytes("ISO8859-1"), "utf-8"));
        goods.setPrice(Double.valueOf(map.get("price")));
        goods.setRatio(Double.valueOf(map.get("ratio")));
        goods.setLocation(new String(map.get("location").getBytes("ISO8859-1"), "utf-8"));
        goods.setType(Integer.valueOf(map.get("type")));
        goods.setUId(Integer.valueOf(map.get("uId")));
        switch (imageNames.size()) {
            case 6:
                goods.setImg6(imageNames.get(5));
            case 5:
                goods.setImg5(imageNames.get(4));
            case 4:
                goods.setImg4(imageNames.get(3));
            case 3:
                goods.setImg3(imageNames.get(2));
            case 2:
                goods.setImg2(imageNames.get(1));
            case 1:
                goods.setImg1(imageNames.get(0));
                break;
        }
        System.out.println(goods.toString());
        if (goodsService.save(goods)) {
            return Return.ok();
        } else {
            return Return.error();
        }
    }

    @ResponseBody
    @RequestMapping("getBanner")
    public Map getBanner() {
        return Return.ok(goodsService.getBanner());
    }

    @ResponseBody
    @RequestMapping("getRollText")
    public Map getRollText() {
        List<GoodsUserVO> list = goodsService.getRollText();
        Map<String, Integer> map = new HashMap<>();
        for (GoodsUserVO g : list) {
            map.put("【求购】" + g.getName(), g.getId());
        }
        return Return.ok(map);
    }

    @ResponseBody
    @RequestMapping("getGoodsById")
    public Map getGoodsById(@RequestBody Map map) {
        int gid = (int) map.get("gid");
        GoodsUserVO goods = goodsService.getGoods(gid);
        if (null != goods) {
            return Return.ok(goods);
        } else {
            return Return.error();
        }
    }

    @ResponseBody
    @RequestMapping("viewGoods")
    public Map viewGoods(@RequestBody Map map) {
        int gid = (int) map.get("gid");
        goodsService.viewGoods(gid);
        GoodsUserVO goods = goodsService.getGoods(gid);
        if (null != goods) {
            return Return.ok(goods);
        } else {
            return Return.error();
        }
    }

    @ResponseBody
    @RequestMapping("listCommentByGid")
    public Map listCommentByGid(@RequestBody Map map) {
        int gid = (int) map.get("gid");
        List<CommentUserVO> list = goodsService.listCommentByGid(gid);
        if (null != list) {
            return Return.ok(list);
        } else {
            return Return.error();
        }
    }

    @ResponseBody
    @RequestMapping("searchGoods")
    public Map searchGoods(@RequestBody Map map) {
        String param = (String) map.get("param");
        param = param.replace(" ", "");
        StringBuilder builder = new StringBuilder(param);
        for (int i = 0; i < builder.length() + 1; i = i + 2) {
            builder.insert(i, "*");
        }
        List<GoodsUserVO> list = goodsService.searchGoods(builder.toString());
        if (null != list) {
            return Return.ok(list);
        } else {
            return Return.error();
        }
    }

    @ResponseBody
    @RequestMapping("listFocusGoods")
    public Map listFocusGoods(@RequestBody Map<String, Integer> map) {
        Integer pageSize = map.get("pageSize");
        Integer pageNumber = map.get("pageNumber");
        Integer uid = map.get("uid");
        List<GoodsUserVO> list = goodsService.listFocusGoods(uid, pageNumber, pageSize);
        if (null != list) {
            return Return.ok(list);
        } else {
            return Return.error();
        }
    }

    /**
     * 发布求购
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("addBuy")
    public Map addBuy(@RequestBody Map map) {
        Goods goods = new Goods();
        goods.setType(2);
        goods.setUId((Integer) map.get("uId"));
        goods.setPrice(Double.valueOf(String.valueOf(map.get("price"))));
        goods.setLocation((String) map.get("location"));
        goods.setDes((String) map.get("des"));
        goods.setName((String) map.get("name"));
        return goodsService.save(goods) ?
                Return.ok(1) : Return.ok(0);
    }

}
