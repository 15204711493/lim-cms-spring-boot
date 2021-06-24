package io.github.talelin.latticy.laver.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.laver.mapper.SpecKeyMapper;
import io.github.talelin.latticy.laver.model.SpecKeyDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecKeyService extends ServiceImpl<SpecKeyMapper,SpecKeyDO> {

    public List<SpecKeyDO> getBySpuId(Integer id){
         return this.getBaseMapper().getBySpuId(id);
    }
}
