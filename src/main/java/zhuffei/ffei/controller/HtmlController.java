package zhuffei.ffei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhuwei
 * @version 1.0
 * @date 2020/4/27 17:26
 */
@Controller
@RequestMapping("/ffei")
public class HtmlController {

  @RequestMapping("/admin")
  public String admin() {
    return "admin";
  }
}
