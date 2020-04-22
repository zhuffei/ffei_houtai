package zhuffei.ffei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhuffei
 * @date 2020.3.4
 */
@Data
@TableName("user")
public class User {

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("pwd")
    private String pwd;

    @TableField("state")
    private int state;

    @TableField("avator")
    private String avator;

    public User(String name, String phone, String pwd) {
        this.name = name;
        this.phone = phone;
        this.pwd = pwd;
    }

    public User() {
    }
}
