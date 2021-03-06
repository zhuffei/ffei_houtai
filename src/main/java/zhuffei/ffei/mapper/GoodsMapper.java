package zhuffei.ffei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import zhuffei.ffei.entity.CommentUserVO;
import zhuffei.ffei.entity.Goods;

import java.util.List;
import java.util.Map;

import zhuffei.ffei.entity.GoodsUserVO;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/9 15:12
 */
//@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

  List<GoodsUserVO> listRecentGoods(@Param("pageSize") int pageSize, @Param("pageNumber") int pageNumber);

  List<GoodsUserVO> getBanner();

  List<GoodsUserVO> getRollText();

  GoodsUserVO getGoods(@Param("gid") int gid);

  int viewGoods(@Param("gid") int gid);

  List<CommentUserVO> listCommentByGid(@Param("gid") Integer gid);

  List<GoodsUserVO> searchGoods(@Param("param") String param);

  List<GoodsUserVO> listFocusGoods(@Param("uid") int uid, @Param("pageNumber") int pageNumber,
      @Param("pageSize") int pageSize);

  int banGoods(@Param("gid") int gid);

  int checkWall(@Param("gid") int gid);

  int upWallBanner(@Param("gid") int gid);

  int upWallText(@Param("gid") int gid);

  int getState(@Param("gid") int gid);

  int setState(@Param("gid") int gid, @Param("state") int state);

  Integer countSellGoods();

  Integer countBuyGoods();

  Integer countOrders();

  Integer countOrdersTotalMoney();

  Integer countRollTextIncome();

  Integer countBannerIncome();

  Integer countNewUser();

  List<Goods> listUncheckGoods(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

  Integer countUnckeckGoods();

  List<Map> listReportedGoods(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

  Integer countReportedGoods();


}
