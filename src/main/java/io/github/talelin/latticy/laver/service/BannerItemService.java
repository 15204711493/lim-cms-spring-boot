package io.github.talelin.latticy.laver.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.laver.dto.BannerItemDTO;
import io.github.talelin.latticy.laver.mapper.BannerItemMapper;
import io.github.talelin.latticy.laver.model.BannerItemDO;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerItemService extends ServiceImpl<BannerItemMapper, BannerItemDO> {


    public void updateBannerItem(BannerItemDTO bannerItemDTO,Long id){
        BannerItemDO bannerItemDo = getById(id);
        if(bannerItemDo == null){
            throw  new NotFoundException(20001);
        }
        BeanUtils.copyProperties(bannerItemDTO,bannerItemDo);
        this.updateById(bannerItemDo);
    }

    public void deleteBannerItem(Long id){
        BannerItemDO bannerItemDo = getById(id);
        if(bannerItemDo == null){
            throw  new NotFoundException(20001);
        }
        this.getBaseMapper().deleteById(id);
    }



}
