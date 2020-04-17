package zhuffei.ffei.entity;

import lombok.Data;

@Data
public class CommentUserVO {
    private Integer id;
    private Integer uId;
    private String userName;
    private Integer gId;
    private String comment;
    private String createTime;
    private String avator;
}
