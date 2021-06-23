package io.github.talelin.latticy.laver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.laver.bo.BannerWithItemsBO;
import io.github.talelin.latticy.laver.dto.BannerDTO;
import io.github.talelin.latticy.laver.mapper.BannerItemMapper;
import io.github.talelin.latticy.laver.mapper.BannerMapper;
import io.github.talelin.latticy.laver.model.BannerDO;
import io.github.talelin.latticy.laver.model.BannerItemDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService extends ServiceImpl<BannerMapper, BannerDO> {

    @Autowired
    private BannerItemMapper bannerItemMapper;

    public void update(BannerDTO bannerDTO,Long id){
        BannerDO bannerDO = this.getById(id);
        if(bannerDO == null){
            throw new NotFoundException(20000);
        }
        BeanUtils.copyProperties(bannerDTO,bannerDO);
        this.updateById(bannerDO);
    }

    public void deleteBanner(Long id){
        BannerDO bannerDO =this.getById(id);
        if(bannerDO == null){
            throw  new NotFoundException(20000);
        }
        this.getBaseMapper().deleteById(id);
    }


    public BannerWithItemsBO getBannerWithBannerItems(Long id){
        BannerDO bannerDO = getById(id);
        if(bannerDO == null){
            throw  new NotFoundException(20000);
        }

        QueryWrapper<BannerItemDO> wrapper = new QueryWrapper<>();
        wrapper.eq("banner_id",id);
        List<BannerItemDO> bannerItemDOS = bannerItemMapper.selectList(wrapper);
        return new BannerWithItemsBO(bannerDO,bannerItemDOS);

    }

}
