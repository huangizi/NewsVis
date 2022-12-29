package com.freya.onlinetech;

import com.freya.onlinetech.pojo.CommentsRoot;
import com.freya.onlinetech.pojo.CourseVideo;
import com.freya.onlinetech.pojo.VideoInfo;
import com.freya.onlinetech.service.CommentsService;
import com.freya.onlinetech.service.CourseService;
import com.freya.onlinetech.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class OnlineTechApplicationTests {

    @Autowired
    CommentsService commentsService;

    @Test
    void videoTest()
    {
        List<CommentsRoot> commentsRoot = commentsService.getCommentsRoot(1);
        System.out.println(commentsRoot);
    }

}
