package io.github.talelin.latticy.laver.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.laver.dto.SpuDTO;
import io.github.talelin.latticy.laver.mapper.SpuMapper;
import io.github.talelin.latticy.laver.model.CategoryDO;
import io.github.talelin.latticy.laver.model.SpuDO;
import io.github.talelin.latticy.laver.model.SpuDetailDO;
import io.github.talelin.latticy.laver.model.SpuImgDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpuService extends ServiceImpl<SpuMapper, SpuDO> {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpuImgService spuImgService;

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

    public void create(SpuDTO spuDTO){
        SpuDO spuDO = new SpuDO();
        BeanUtils.copyProperties(spuDTO,spuDO);
        CategoryDO categoryDO = categoryService.getCategoryById(spuDTO.getCategoryId());
        spuDO.setRootCategoryId(categoryDO.getParentId());
        this.save(spuDO);

        List<String> spuImgList =  new ArrayList<>();
        if(spuDTO.getSpuImgList() == null){
            spuImgList.add(spuDTO.getImg());
        }else {
            spuImgList = spuDTO.getSpuImgList();
        }
        this.insertSpuImgList(spuImgList);

    }



    public void insertSpuImgList(List<String> list){
        List<SpuImgDO> spuImgDOList = list.stream().map(s -> {
            SpuImgDO spuImgDO = new SpuImgDO();
            spuImgDO.setImg(s);
            return spuImgDO;
        }).collect(Collectors.toList());

        this.spuImgService.saveBatch(spuImgDOList);
    }


}
