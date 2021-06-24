package io.github.talelin.latticy.laver.controller.v1;

import io.github.talelin.latticy.laver.model.SpecKeyDO;
import io.github.talelin.latticy.laver.service.SpecKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/spec-key")
public class SpecKeyController {
    @Autowired
    private SpecKeyService specKeyService;

    @GetMapping("/by/spu/{id}")
    public List<SpecKeyDO> getBySpuId(@PathVariable @Positive Integer id){
       return this.specKeyService.getBySpuId(id);
    }
}
