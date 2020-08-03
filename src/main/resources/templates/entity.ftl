package ${entryPkg};

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

<#list  beanPkgInfo as pkg>
import ${pkg};
</#list>

@Data
@ApiModel(value="${entry}对象", description="")
public class ${entry} {

<#list  tableInfo as fields>
    @ApiModelProperty(value = "${fields.fieldNote}")
    private ${fields.fieldType} ${fields.field};

</#list>
}