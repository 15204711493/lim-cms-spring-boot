package io.github.talelin.latticy.laver.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.laver.model.BannerItemDO;
import io.github.talelin.latticy.laver.model.SpuDO;
import io.github.talelin.latticy.laver.model.SpuDetailDO;
import io.github.talelin.latticy.laver.service.SpuService;
import io.github.talelin.latticy.vo.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/v1/spu")
@Validated
public class SpuController {

    @Autowired
    private SpuService spuService;

    @GetMapping("/page")
    public PageResponseVO<SpuDO> page(@RequestParam(required = false, defaultValue = "0")
                     @Min(value = 0) Integer page,
                                      @RequestParam(required = false, defaultValue = "10")
                     @Min(value = 1) @Max(value = 30) Integer count){

        Page<SpuDO> spuDOPage = new Page<>(page,count);
        IPage<SpuDO> spuDOPage1 = spuService.getBaseMapper().selectPage(spuDOPage, null);
        //PageResponseVO<SpuDO> vo = new PageResponseVO<SpuDO>((int)spuDOPage1.getTotal(),spuDOPage1.getRecords(),(int)spuDOPage1.getPages(),(int)spuDOPage1.getSize());
        return PageUtil.build(spuDOPage1);

    }


    @GetMapping("/{id}/detail")
    public SpuDetailDO getDetail(@PathVariable @Positive Long id){
        return spuService.getSpuDetail(id);
    }


}
