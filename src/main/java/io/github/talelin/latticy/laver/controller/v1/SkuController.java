package io.github.talelin.latticy.laver.controller.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.latticy.laver.model.SkuDO;
import io.github.talelin.latticy.laver.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/sku")
@Validated
public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping("/by/spu/{id}")
    public List<SkuDO> getBySpuId(@PathVariable  @Positive Long id){
        List<SkuDO> list = this.skuService.lambdaQuery().eq(SkuDO::getSpuId, id).list();
        return list;
    }




}
