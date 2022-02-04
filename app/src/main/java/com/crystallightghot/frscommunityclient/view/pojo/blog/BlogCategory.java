package com.crystallightghot.frscommunityclient.view.pojo.blog;


import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Date 2022/2/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

@Data

public class BlogCategory {


    private Long categoryId;


    private String categoryName;

    private Timestamp createdTime;

    private BlogCategory parent;

    private String description;

    private User user;
}
