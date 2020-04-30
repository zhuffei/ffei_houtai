package zhuffei.ffei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zhuffei.ffei.entity.CommentUserVO;
import zhuffei.ffei.entity.Goods;
import zhuffei.ffei.entity.GoodsUserVO;
import zhuffei.ffei.mapper.GoodsMapper;
import zhuffei.ffei.service.IGoodsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/9 15:16
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

  public List<GoodsUserVO> listRecentGoods(int pageSize, int pageNumber) {
    return baseMapper.listRecentGoods(pageSize, pageNumber);
  }

  @Override
  public List<GoodsUserVO> getBanner() {
    return baseMapper.getBanner();
  }

  @Override
  public List<GoodsUserVO> getRollText() {
    return baseMapper.getRollText();
  }

  @Override
  public GoodsUserVO getGoods(int gid) {
    return baseMapper.getGoods(gid);
  }

  @Override
  public int viewGoods(int gid) {
    return baseMapper.viewGoods(gid);
  }

  @Override
  public List<CommentUserVO> listCommentByGid(Integer gid) {
    return baseMapper.listCommentByGid(gid);
  }

  @Override
  public List<GoodsUserVO> searchGoods(String param) {
    return baseMapper.searchGoods(param);
  }

  @Override
  public List<GoodsUserVO> listFocusGoods(int uid, int pageNumber, int pageSize) {
    return baseMapper.listFocusGoods(uid, pageNumber, pageSize);
  }

  @Override
  public int banGoods(int gid) {
    return baseMapper.banGoods(gid);
  }

  @Override
  public int checkWall(int gid) {
    return baseMapper.checkWall(gid);
  }

  @Override
  public int upWallBanner(int gid) {
    return baseMapper.upWallBanner(gid);
  }

  @Override
  public int upWallText(int gid) {
    return baseMapper.upWallText(gid);
  }

  @Override
  public int getState(int gid) {
    return baseMapper.getState(gid);
  }

  @Override
  public int setState(int gid, int state) {
    return baseMapper.setState(gid, state);
  }

  @Override
  public Map<String, Integer> countData() {
    Map map = new HashMap();
    map.put("sell", baseMapper.countSellGoods());
    map.put("buy", baseMapper.countBuyGoods());
    map.put("orders", baseMapper.countOrders());
    map.put("money", baseMapper.countOrdersTotalMoney());
    map.put("banner", baseMapper.countBannerIncome());
    map.put("text", baseMapper.countRollTextIncome());
    map.put("user", baseMapper.countNewUser());
    return map;
  }

  @Override
  public List<Goods> listUncheckGoods(int pageNumber, int pageSize) {
    return baseMapper.listUncheckGoods(pageNumber, pageSize);
  }

  @Override
  public Integer countUnckeckGoods() {
    return baseMapper.countUnckeckGoods();
  }

  @Override
  public List<Goods> listReportedGoods(int pageNumber, int pageSize) {
    return baseMapper.listReportedGoods(pageNumber, pageSize);
  }

    @Override
    public Integer countReportedGoods() {
        return baseMapper.countReportedGoods();
    }


}
