package io.github.talelin.latticy.laver.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("banner")
public class BannerDO {

    private Integer id;

    private String name;

    private String title;

    private String description;

    private String img;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;
}
