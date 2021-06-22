package io.github.talelin.latticy.laver.bo;

import io.github.talelin.latticy.laver.model.BannerDO;
import io.github.talelin.latticy.laver.model.BannerItemDO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
public class BannerWithItemsBO {

    private Integer id;

    private String name;

    private String title;

    private String description;

    private String img;

    private List<BannerItemDO> bannerItemDOS;

    public BannerWithItemsBO(BannerDO bannerDO,List<BannerItemDO> bannerItemDOS){
        BeanUtils.copyProperties(bannerDO,this);
        this.setBannerItemDOS(bannerItemDOS);
    }

}
