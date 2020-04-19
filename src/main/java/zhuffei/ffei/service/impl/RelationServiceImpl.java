package zhuffei.ffei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhuffei.ffei.mapper.RelationMapper;
import zhuffei.ffei.service.IRelationService;

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
        return relationMapper.checkCollect(gid,uid);
    }

    @Override
    public Integer collect(int gid, int uid) {
        return relationMapper.collect(gid,uid);
    }

    @Override
    public Integer cancelCollect(int gid, int uid) {
        return relationMapper.cancelCollect(gid,uid);
    }

    @Override
    public Integer report(int gid, int uid, String reason) {
        return relationMapper.report(gid,uid,reason);
    }
}
