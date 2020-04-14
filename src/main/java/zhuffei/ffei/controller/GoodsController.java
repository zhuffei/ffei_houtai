package zhuffei.ffei.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
   *
   * @param request
   * @return
   */
  @ResponseBody
  @RequestMapping("listRecentGoods")
  public Map listRecentGoods(HttpServletRequest request) {     //必须传入每页的条数和页数
//        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
//        Integer pageNumber = Integer.valueOf(request.getParameter("pageNumber"));

    Integer pageSize = 1;
    Integer pageNumber = 1;
    List<Goods> data = goodsService.listRecentGoods(pageSize, pageNumber);
    System.out.println(data);
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
    goods.setName(new String(map.get("title").getBytes("ISO8859-1"),"utf-8"));
    goods.setDescribe(new String(map.get("describe").getBytes("ISO8859-1"),"utf-8"));
    goods.setPrice(Double.valueOf(map.get("price")));
    goods.setLocation(new String(map.get("location").getBytes("ISO8859-1"),"utf-8"));
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
    if(goodsService.save(goods)){
      return Return.ok();
    }else{
      return Return.error();
    }

  }
}
