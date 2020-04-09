package zhuffei.ffei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhuffei.ffei.entity.User;
import zhuffei.ffei.mapper.UserMapper;
import zhuffei.ffei.service.IUserService;

/**
 * @author zhuffei
 * @date 2020.3.4
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
