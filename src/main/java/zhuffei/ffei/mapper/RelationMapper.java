package zhuffei.ffei.mapper;

import org.apache.ibatis.annotations.Param;
import zhuffei.ffei.entity.Goods;

import java.util.Date;
import java.util.List;
import java.util.Map;

import zhuffei.ffei.entity.User;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/18 13:00
 */
public interface RelationMapper {

    Integer checkCollect(@Param("gid") int gid, @Param("uid") int uid);

    Integer collect(@Param("gid") int gid, @Param("uid") int uid);

    Integer cancelCollect(@Param("gid") int gid, @Param("uid") int uid);

    Integer report(@Param("gid") int gid, @Param("uid") int uid, @Param("reason") String reason);

    Integer comment(@Param("gid") int gid, @Param("uid") int uid, @Param("comment") String comment);

    Integer deleteComment(@Param("id") int id);

    List<Goods> listCollectGoods(@Param("uid") int uid);

    List<Goods> listMyGoods(@Param("uid") int uid);

    List<Goods> listMySell(@Param("uid") int uid);

    List<Goods> listMyBuy(@Param("uid") int uid);

    List<User> listFocus(@Param("uid") int uid);

    List<User> listFans(@Param("uid") int uid);

    Integer countFocus(@Param("uid") int uid);

    Integer countFans(@Param("uid") int uid);

    Integer checkFocus(@Param("focuser") int focuser, @Param("focused") int focused);

    Integer focus(@Param("focuser") int focuser, @Param("focused") int focused);

    Integer cancelFocus(@Param("focuser") int focuser, @Param("focused") int focused);

    Integer buy(@Param("gid") int gid, @Param("uid") int uid);

    Integer changeGoodsState(@Param("gid") int gid, @Param("state") int state);

    Integer handleReport(@Param("gid") int gid);

    Integer login(@Param("uid") int uid);

    List<Map> getLoginData(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Map> getReleaseData(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
