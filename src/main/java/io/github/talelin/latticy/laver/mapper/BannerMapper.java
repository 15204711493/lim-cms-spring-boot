package io.github.talelin.latticy.laver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.laver.model.BannerDO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerMapper extends BaseMapper<BannerDO> {

//    @Select("select * from banner " +
//            "join banner_item on banner.id = banner_item.banner_id " +
//            "where banner.id = #{id}")
//    BannerDO getBannerById(Long Id);
}
