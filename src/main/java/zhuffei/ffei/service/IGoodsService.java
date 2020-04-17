package zhuffei.ffei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import zhuffei.ffei.entity.CommentUserVO;
import zhuffei.ffei.entity.Goods;

import java.util.List;
import zhuffei.ffei.entity.GoodsUserVO;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/9 15:16
 */
public interface IGoodsService extends IService<Goods> {

  List<GoodsUserVO> listRecentGoods(int pageSize, int pageNumber);

  List<GoodsUserVO> getBanner();

  List<GoodsUserVO> getRollText();

  GoodsUserVO getGoods(int gid);

  int viewGoods(int gid);

  List<CommentUserVO> listCommentByGid( Integer gid);
}
