package ${serviceImplPkg};

import ${entryPkg}.${entry};
import ${repositoryPkg}.${entry}Repository;
import ${servicePkg}.${entry}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ${entry}ServiceImpl implements ${entry}Service {
    @Autowired
    private ${entry}Repository baseRepository;

    public Page<${entry}> page(int page, int size) {
        return baseRepository.findAll(PageRequest.of(page-1, size));
    }

    @Override
    public Optional<${entry}> findById(Integer id) {
        return baseRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        baseRepository.deleteById(id);
    }

    @Override
    public ${entry} save(${entry} param) {
        return baseRepository.save(param);
    }

    @Override
    public void saveAll(List<${entry}> logList) {
        baseRepository.saveAll(logList);
    }
}
