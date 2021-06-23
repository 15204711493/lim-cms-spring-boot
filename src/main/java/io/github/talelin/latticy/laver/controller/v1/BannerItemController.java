package io.github.talelin.latticy.laver.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.laver.dto.BannerItemDTO;
import io.github.talelin.latticy.laver.model.BannerItemDO;
import io.github.talelin.latticy.laver.service.BannerItemService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/v1/banner-item")
@Validated
public class BannerItemController {

    @Autowired
    private BannerItemService bannerItemService;

    @PostMapping
    public CreatedVO create(@RequestBody BannerItemDTO bannerItemDTO){
        BannerItemDO bannerItemDO = new BannerItemDO();
        BeanUtils.copyProperties(bannerItemDTO,bannerItemDO);
        bannerItemService.save(bannerItemDO);
        return  new CreatedVO();
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@RequestBody BannerItemDTO bannerItemDTO, @PathVariable @Positive Long id){
        bannerItemService.updateBannerItem(bannerItemDTO,id);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable Long id){
        bannerItemService.deleteBannerItem(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    public BannerItemDO getBannerItem(@PathVariable @Positive Long id){
        BannerItemDO bannerItemDO = bannerItemService.getById(id);
        if(bannerItemDO == null){
            throw new NotFoundException(20001);
        }
        return bannerItemDO;
    }

    @GetMapping("/page")
    public PageResponseVO<BannerItemDO> page(@RequestParam(required = false,defaultValue = "0") @Min(value = 0) Integer page,
                                             @RequestParam(required = false,defaultValue = "10") @Min(value = 1) @Max(value = 10) Integer count){

        Page<BannerItemDO> bannerItemDOPage = new Page<BannerItemDO>(page,count);
        IPage<BannerItemDO> page1 = bannerItemService.getBaseMapper().selectPage(bannerItemDOPage,null);
        //PageResponseVO<BannerItemDO> vo = new PageResponseVO<BannerItemDO>((int)page1.getTotal(),page1.getRecords(),(int)page1.getPages(),(int)page1.getSize());
        return PageUtil.build(page1);

    }

}
