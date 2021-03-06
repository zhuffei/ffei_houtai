package zhuffei.ffei.entity;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

@Data
public class GoodsUserVO {
    private int id;
    private int uId;
    private String userName;
    private String avator;
    private String name;
    private String des;
    private Double price;
    private String accid;
    private int type;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String img6;
    private double ratio;
    private String location;
    private String state;
    private Integer browse;
    private Date createTime;
    private Date updateTime;
}
