package com.freya.onlinetech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsRoot {
    private int comment_id;
    private String type;  //父评论的类型
    private int owner_id;
    private int from_id;
    private String content;
    private Date create_time;
    private String from_identity;
    private String owner_identity;
    private String owner_name;
    private String from_name;
    private String owner_avatar;
    private String from_avatar;
    //一个父评论拥有多个子评论
    private List<CommentsReply> commentsReplyList;
}
