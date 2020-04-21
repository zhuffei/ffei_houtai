package zhuffei.ffei.mapper;

import org.apache.ibatis.annotations.Param;
import zhuffei.ffei.entity.Goods;

import java.util.List;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/18 13:00
 */
public interface RelationMapper {

    Integer checkCollect(@Param("gid") int gid,@Param("uid") int uid);

    Integer collect(@Param("gid") int gid,@Param("uid") int uid);

    Integer cancelCollect(@Param("gid") int gid,@Param("uid") int uid);

    Integer report(@Param("gid")int gid,@Param("uid") int uid,@Param("reason")String reason);

    Integer comment(@Param("gid")int gid,@Param("uid") int uid,@Param("comment")String comment);

    Integer deleteComment(@Param("id") int id);

    List<Goods> listCollectGoods(@Param("uid") int uid);

    List<Goods> listMyGoods(@Param("uid") int uid);

    List<Goods> listMySell(@Param("uid") int uid);

    List<Goods> listMyBuy(@Param("uid")int uid);
}
