package io.github.talelin.latticy.laver.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.core.annotation.PermissionModule;
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
import java.util.List;

@RestController
@RequestMapping("/v1/banner")
@Validated
@PermissionModule(value = "Banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/page")
    @LoginRequired
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
    @PermissionMeta(value = "更新Banner")
    @GroupRequired
    public UpdatedVO update(@RequestBody @Validated BannerDTO bannerDTO,@PathVariable @Positive Long id){
        bannerService.update(bannerDTO,id);
        return new UpdatedVO();
    }


    @DeleteMapping("/{id}")
    @PermissionMeta(value = "删除Banner")
    @GroupRequired
    public DeletedVO delete(@PathVariable @Positive Long id){
        bannerService.deleteBanner(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    @LoginRequired
    public BannerWithItemsBO getBannerWithBannerItems(@PathVariable @Positive Long id){
      return bannerService.getBannerWithBannerItems(id);
    }

    @PostMapping
    @PermissionMeta(value = "创建Banner")
    @GroupRequired
    public CreatedVO create(@RequestBody @Validated BannerDTO bannerDTO){
        BannerDO bannerDO = new BannerDO();
        BeanUtils.copyProperties(bannerDTO,bannerDO);
        bannerService.save(bannerDO);
        return new CreatedVO();
    }

}
