package com.freya.onlinetech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsReply {
    private int reply_id;
    private int comment_id;
    private int from_id;
    private int to_id;
    private String from_identity;
    private String to_identity;
    private String content;
    private Date create_time;
    private String from_name;
    private String to_name;
    private String from_avatar;
    private String to_avatar;
}
