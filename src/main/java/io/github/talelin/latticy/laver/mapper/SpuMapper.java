package io.github.talelin.latticy.laver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.laver.model.SpuDO;
import io.github.talelin.latticy.laver.model.SpuDetailDO;
import org.springframework.stereotype.Repository;

@Repository
public interface SpuMapper extends BaseMapper<SpuDO> {

    SpuDetailDO getDetail(Long id);
}
