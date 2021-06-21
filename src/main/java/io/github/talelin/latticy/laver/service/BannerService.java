package io.github.talelin.latticy.laver.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.laver.dto.BannerDTO;
import io.github.talelin.latticy.laver.mapper.BannerMapper;
import io.github.talelin.latticy.laver.model.BannerDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService extends ServiceImpl<BannerMapper, BannerDO> {

    public void update(BannerDTO bannerDTO,Long id){
        BannerDO bannerDO = this.getById(id);
        if(bannerDO == null){
            throw new NotFoundException(20000);
        }
        BeanUtils.copyProperties(bannerDTO,bannerDO);
        this.updateById(bannerDO);
    }


}
