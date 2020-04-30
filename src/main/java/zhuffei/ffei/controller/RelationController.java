package zhuffei.ffei.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zhuffei.ffei.Tool.Return;
import zhuffei.ffei.entity.User;
import zhuffei.ffei.service.IRelationService;

import java.security.spec.ECField;
import java.util.Map;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/18 13:00
 */
@RestController
@RequestMapping("/relation")
public class RelationController {

  @Autowired
  IRelationService relationService;

  @ResponseBody
  @RequestMapping("checkCollect")
  public Map checkCollection(@RequestBody Map map) {
    try {
      Integer gid = (Integer) map.get("gid");
      Integer uid = (Integer) map.get("uid");
      return Return.ok(relationService.checkCollect(gid, uid));
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("cancelCollect")
  public Map cancelCollect(@RequestBody Map map) {
    try {
      Integer gid = (Integer) map.get("gid");
      Integer uid = (Integer) map.get("uid");
      return Return.ok(relationService.cancelCollect(gid, uid));
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("collect")
  public Map collect(@RequestBody Map map) {
    try {
      Integer gid = (Integer) map.get("gid");
      Integer uid = (Integer) map.get("uid");
      return Return.ok(relationService.collect(gid, uid));
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("report")
  public Map report(@RequestBody Map map) {
    try {
      Integer gid = (Integer) map.get("gid");
      Integer uid = (Integer) map.get("uid");
      String reason = (String) map.get("reason");
      return Return.ok(relationService.report(gid, uid, reason));
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("comment")
  public Map comment(@RequestBody Map map) {
    try {
      Integer gid = (Integer) map.get("gid");
      Integer uid = (Integer) map.get("uid");
      String comment = (String) map.get("comment");
      return Return.ok(relationService.comment(gid, uid, comment));
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("deleteComment")
  public Map deleteComment(@RequestBody Map map) {
    try {
      Integer id = (Integer) map.get("id");
      return Return.ok(relationService.deleteComment(id));
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("listMyGoods")
  public Map listMyGoods(@RequestBody Map map) {
    try {

      Integer uid = (Integer) map.get("uid");
      return Return.ok(relationService.listMyGoods(uid));
    } catch (Exception e) {
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("listCollectGoods")
  public Map listCollectGoods(@RequestBody Map map) {
    try {

      Integer uid = (Integer) map.get("uid");
      return Return.ok(relationService.listCollectGoods(uid));
    } catch (Exception e) {
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("listMySell")
  public Map listMySell(@RequestBody Map map) {
    try {

      Integer uid = (Integer) map.get("uid");
      return Return.ok(relationService.listMySell(uid));
    } catch (Exception e) {
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("listMyBuy")
  public Map listMyBuy(@RequestBody Map map) {
    try {

      Integer uid = (Integer) map.get("uid");
      return Return.ok(relationService.listMyBuy(uid));
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("listFocus")
  public Map listFocus(@RequestBody Map map) {
    try {

      Integer uid = (Integer) map.get("uid");
      List<User> list = relationService.listFocus(uid);
      for (User user : list) {
        user.setIsFocused(1);
      }
      return Return.ok(list);
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("listFans")
  public Map listFans(@RequestBody Map map) {
    try {
      Integer uid = (Integer) map.get("uid");
      List<User> list = relationService.listFans(uid);
      List<User> focus = relationService.listFocus(uid);
      List<Integer> focusId = new ArrayList<>();
      for (User user : focus) {
        focusId.add(user.getId());
      }
      for (User user : list) {
        user.setIsFocused(focusId.contains(user.getId()) ? 1 : 0);
      }
      return Return.ok(list);
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("countFocusAndFans")
  public Map countFocusAndFans(@RequestBody Map map) {
    try {

      Integer uid = (Integer) map.get("uid");
      return Return.ok(relationService.countFocusAndFans(uid));
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }


  @ResponseBody
  @RequestMapping("focus")
  public Map focus(@RequestBody Map map) {
    try {
      Integer focuser = (Integer) map.get("focuser");
      Integer focused = (Integer) map.get("focused");
      if (relationService.checkFocus(focuser, focused) == 0) {
        return Return.ok(relationService.focus(focuser, focused));
      } else {
        return Return.ok(1);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("cancelFocus")
  public Map cancelFocus(@RequestBody Map map) {
    try {
      Integer focuser = (Integer) map.get("focuser");
      Integer focused = (Integer) map.get("focused");
      if (relationService.checkFocus(focuser, focused) == 1) {
        return Return.ok(relationService.cancelFocus(focuser, focused));
      } else {
        return Return.ok(1);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("checkFocus")
  public Map checkFocus(@RequestBody Map map) {
    try {
      Integer focuser = (Integer) map.get("focuser");
      Integer focused = (Integer) map.get("focused");
      return Return.ok(relationService.checkFocus(focuser, focused));
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("buy")
  public Map buy(@RequestBody Map map) {
    try {
      Integer gid = (Integer) map.get("gid");
      Integer uid = (Integer) map.get("uid");
      if (relationService.buy(gid, uid) == 1 && relationService.changeGoodsState(gid, 2) == 1) {
        return Return.ok(1);
      } else {
        return Return.error();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return Return.error();
    }
  }

  @ResponseBody
  @RequestMapping("handleReport")
  public Map handleReport(@RequestBody Map map) {
    Integer gid = (Integer) map.get("gid");
    return Return.ok(relationService.handleReport(gid));
  }
}
