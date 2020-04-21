package zhuffei.ffei.service;

import org.apache.ibatis.annotations.Param;
import zhuffei.ffei.entity.Goods;

import java.util.List;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/18 13:01
 */
public interface IRelationService {

    Integer checkCollect(int gid, int uid);

    Integer collect(int gid, int uid);

    Integer cancelCollect(int gid, int uid);

    Integer report(int gid, int uid, String reason);

    Integer comment(int gid, int uid, String comment);

    Integer deleteComment(int id);

    List<Goods> listCollectGoods(int uid);

    List<Goods> listMyGoods(int uid);

    List<Goods> listMySell(int uid);

    List<Goods> listMyBuy(int uid);
}
