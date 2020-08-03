package ${controllerPkg};

import ${entryPkg}.${entry};
import ${servicePkg}.${entry}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@Api(tags = "")
@RestController
@RequestMapping("/api")
public class ${entry}Controller {

    @Autowired
    private ${entry}Service baseService;

    @GetMapping("")
    @ApiOperation("查询")
    public ResponseEntity<Page<${entry}>> list(int page, int size) {
        Page<${entry}> discussLogs = baseService.page(page, size);
        return ResponseEntity.ok(discussLogs);
    }

    @PostMapping
    @ApiOperation("保存或更新")
    public ResponseEntity<${entry}> saveOrUpdate(${entry} param) {
        return ResponseEntity.ok(baseService.save(param));
    }

    @ApiOperation("批量添加删除")
    @PostMapping(value = "/batch")
    public ResponseEntity.BodyBuilder addOrUpdateBatch(@RequestBody ${entry}[] entities) {
        baseService.saveAll(Arrays.asList(entities));
        return ResponseEntity.ok();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResponseEntity.BodyBuilder deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            baseService.deleteById(id);
        }
        return ResponseEntity.ok();
    }

}
