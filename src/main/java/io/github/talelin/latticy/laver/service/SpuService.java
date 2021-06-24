package io.github.talelin.latticy.laver.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.laver.mapper.SpuMapper;
import io.github.talelin.latticy.laver.model.SpuDO;
import io.github.talelin.latticy.laver.model.SpuDetailDO;
import org.springframework.stereotype.Service;

@Service
public class SpuService extends ServiceImpl<SpuMapper, SpuDO> {

    public SpuDetailDO getSpuDetail(Long id){
      return  this.getBaseMapper().getDetail(id);
    }


    public void delete(Integer id){
        SpuDO exist = this.getById(id);
        if (exist == null) {
            throw new NotFoundException(70000);
        }
        this.getBaseMapper().deleteById(id);
    }
}
