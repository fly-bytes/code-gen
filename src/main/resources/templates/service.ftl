package ${servicePkg};

import ${entryPkg}.${entry};
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ${entry}Service {

    Page<${entry}> page(int page, int size);

    Optional<${entry}> findById(Integer id);

    void deleteById(Integer id);

    ${entry} save(${entry} param);

    void saveAll(List<${entry}> asList);
}
