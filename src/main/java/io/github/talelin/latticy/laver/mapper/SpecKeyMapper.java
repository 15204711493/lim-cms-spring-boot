package io.github.talelin.latticy.laver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.laver.model.SpecKeyDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecKeyMapper extends BaseMapper<SpecKeyDO> {

    List<SpecKeyDO> getBySpuId(Integer id);
}
