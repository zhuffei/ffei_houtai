package zhuffei.ffei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zhuffei.ffei.Tool.Return;
import zhuffei.ffei.service.IRelationService;

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
}
