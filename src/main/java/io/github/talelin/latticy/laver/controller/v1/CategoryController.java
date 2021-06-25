package io.github.talelin.latticy.laver.controller.v1;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import io.github.talelin.latticy.laver.model.CategoryDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import org.springframework.web.bind.annotation.RestController;

/**
* @author generator@TaleLin
* @since 2021-06-25
*/
@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @PostMapping("")
    public CreatedVO create() {
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    public UpdatedVO update(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    public CategoryDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        return null;
    }

    @GetMapping("/page")
    public PageResponseVO<CategoryDO> page(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count
    ) {
        return null;
    }

}
