package io.github.talelin.latticy.laver.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.laver.dto.SpuDTO;
import io.github.talelin.latticy.laver.mapper.SpuMapper;
import io.github.talelin.latticy.laver.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpuService extends ServiceImpl<SpuMapper, SpuDO> {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpuImgService spuImgService;

    @Autowired
    private SpuDetailImgService spuDetailImgService;

    @Autowired
    private SpuKeyService spuKeyService;

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

    @Transactional
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
        this.insertSpuImgList(spuImgList,spuDO.getId());
        if(spuDTO.getSpuDetailImgList() != null){
            this.insertSpuDetailImgList(spuDTO.getSpuDetailImgList(),spuDO.getId());
        }

        if(spuDTO.getSpecKeyIdList() != null){
            this.insertSpuKeyList(spuDTO.getSpecKeyIdList(),spuDO.getId());
        }
    }



    public void insertSpuImgList(List<String> list,Integer spuId){
        List<SpuImgDO> spuImgDOList = list.stream().map(s -> {
            SpuImgDO spuImgDO = new SpuImgDO();
            spuImgDO.setImg(s);
            spuImgDO.setSpuId(spuId);
            return spuImgDO;
        }).collect(Collectors.toList());

        this.spuImgService.saveBatch(spuImgDOList);

    }

    public void insertSpuDetailImgList(List<String> list,Integer spuId){

        ArrayList<SpuDetailImgDO> spuDetailImgDOS = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            SpuDetailImgDO spuDetailImgDO = new SpuDetailImgDO();
            spuDetailImgDO.setImg(list.get(i));
            spuDetailImgDO.setSpuId(spuId);
            spuDetailImgDO.setIndex(i);
            spuDetailImgDOS.add(spuDetailImgDO);
        }
        this.spuDetailImgService.saveBatch(spuDetailImgDOS);
    }



    public void insertSpuKeyList(List<Integer> list,Integer spuId){
        List<SpuKeyDO> spuKeyDOList = list.stream().map(specKeyId -> {
            SpuKeyDO spuKeyDO = new SpuKeyDO();
            spuKeyDO.setSpuId(spuId);
            spuKeyDO.setSpecKeyId(specKeyId);
            return spuKeyDO;
        }).collect(Collectors.toList());
        this.spuKeyService.saveBatch(spuKeyDOList);
    }

}
