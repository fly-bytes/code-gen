package ${repositoryPkg};

import org.springframework.data.repository.PagingAndSortingRepository;
import ${entryPkg}.${entry};
<#if repositoryFkPkg??>
import ${repositoryFkPkg};
</#if>

public interface ${entry}Repository extends PagingAndSortingRepository<${entry}, ${repositoryFkType}> {

}
