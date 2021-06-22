package io.github.talelin.latticy.laver.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.laver.bo.BannerWithItemsBO;
import io.github.talelin.latticy.laver.dto.BannerDTO;
import io.github.talelin.latticy.laver.model.BannerDO;
import io.github.talelin.latticy.laver.service.BannerService;
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
@RequestMapping("/v1/banner")
@Validated
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/page")
    public PageResponseVO<BannerDO> getBanners(@RequestParam(required = false, defaultValue = "0")
                                      @Min(value = 0) Integer page,
                                      @RequestParam(required = false, defaultValue = "10")
                                      @Min(value = 1) @Max(value = 30) Integer count) {
        Page<BannerDO> bannerDOPage = new Page<>(page, count);
        IPage<BannerDO> paging = bannerService.getBaseMapper().selectPage(bannerDOPage, null);
        PageResponseVO<BannerDO> responseVO =
                new PageResponseVO<BannerDO>((int) paging.getTotal(),paging.getRecords(),(int)paging.getPages(),(int)paging.getSize());
        return responseVO;

    }

    @PutMapping("/{id}")
    public UpdatedVO update(@RequestBody @Validated BannerDTO bannerDTO,@PathVariable @Positive Long id){
        bannerService.update(bannerDTO,id);
        return new UpdatedVO();
    }


    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive Long id){
        bannerService.deleteBanner(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    public BannerWithItemsBO getBannerWithBannerItems(@PathVariable @Positive Long id){
      return bannerService.getBannerWithBannerItems(id);
    }

    @PostMapping
    public CreatedVO create(@RequestBody @Validated BannerDTO bannerDTO){
        BannerDO bannerDO = new BannerDO();
        BeanUtils.copyProperties(bannerDTO,bannerDO);
        bannerService.save(bannerDO);
        return new CreatedVO();
    }

}
