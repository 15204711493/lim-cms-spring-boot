package io.github.talelin.latticy.laver.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.laver.mapper.CategoryMapper;
import io.github.talelin.latticy.laver.model.CategoryDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2021-06-25
 */
@Service
public class CategoryService extends ServiceImpl<CategoryMapper,CategoryDO> {

    public CategoryDO getCategoryById(Integer id){
        CategoryDO categoryDo = this.getById(id);
        if(categoryDo == null){
            throw new NotFoundException(40000);
        }
        return categoryDo;
    }

}
