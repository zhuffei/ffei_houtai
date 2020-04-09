package zhuffei.ffei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/9 14:52
 */

@Data
@TableName("goods")
public class Goods {
    @TableId(value="id",type= IdType.AUTO)
    private int id;

    @TableField("uid")
    private Integer uId;

    @TableField("name")
    private String name;

    @TableField("goods_describe")
    private String describe;

    @TableField("price")
    private Double price;

    @TableField("type")
    private Integer type;

    @TableField("state")
    private Integer state;

    @TableField("location")
    private String location;

    @TableField("img1")
    private String img1;

    @TableField("img2")
    private String img2;

    @TableField("img3")
    private String img3;

    @TableField("img4")
    private String img4;

    @TableField("img5")
    private String img5;

    @TableField("browse")
    private Integer browse;

    @TableField("create_time")
    private Timestamp createTime;
}
