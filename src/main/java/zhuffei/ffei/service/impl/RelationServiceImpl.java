package zhuffei.ffei.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhuffei.ffei.entity.Goods;
import zhuffei.ffei.entity.User;
import zhuffei.ffei.mapper.RelationMapper;
import zhuffei.ffei.service.IRelationService;

import java.util.List;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/19 14:42
 */
@Service("relationService")
public class RelationServiceImpl implements IRelationService {

    @Autowired
    RelationMapper relationMapper;

    @Override
    public Integer checkCollect(int gid, int uid) {
        return relationMapper.checkCollect(gid, uid);
    }

    @Override
    public Integer collect(int gid, int uid) {
        return relationMapper.collect(gid, uid);
    }

    @Override
    public Integer cancelCollect(int gid, int uid) {
        return relationMapper.cancelCollect(gid, uid);
    }

    @Override
    public Integer report(int gid, int uid, String reason) {
        return relationMapper.report(gid, uid, reason);
    }

    @Override
    public Integer comment(int gid, int uid, String comment) {
        return relationMapper.comment(gid, uid, comment);
    }

    @Override
    public Integer deleteComment(int id) {
        return relationMapper.deleteComment(id);
    }

    @Override
    public List<Goods> listCollectGoods(int uid) {
        return relationMapper.listCollectGoods(uid);
    }

    @Override
    public List<Goods> listMyGoods(int uid) {
        return relationMapper.listMyGoods(uid);
    }

    @Override
    public List<Goods> listMySell(int uid) {
        return relationMapper.listMySell(uid);
    }

    @Override
    public List<Goods> listMyBuy(int uid) {
        return relationMapper.listMyBuy(uid);
    }

    @Override
    public List<User> listFocus(int uid) {
        return relationMapper.listFocus(uid);
    }

    @Override
    public List<User> listFans(int uid) {
        return relationMapper.listFans(uid);
    }

    @Override
    public Map<String, Integer> countFocusAndFans(int uid) {
        Map<String, Integer> map = new HashMap<>();
        map.put("fans", relationMapper.countFans(uid));
        map.put("focus", relationMapper.countFocus(uid));
        return map;
    }

    @Override
    public Integer checkFocus(int focuser, int focused) {
        return relationMapper.checkFocus(focuser, focused);
    }

    @Override
    public Integer focus(int focuser, int focused) {
        return relationMapper.focus(focuser, focused);
    }

    @Override
    public Integer cancelFocus(int focuser, int focused) {
        return relationMapper.cancelFocus(focuser, focused);
    }

    @Override
    public Integer buy(int gid, int uid) {
        return relationMapper.buy(gid, uid);
    }

    @Override
    public Integer changeGoodsState(int gid, int state) {
        return relationMapper.changeGoodsState(gid, state);
    }

    @Override
    public Integer handleReport(int gid) {
        return relationMapper.handleReport(gid);
    }

}
