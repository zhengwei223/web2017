package ${basePackage}.service.impl;

import ${basePackage}.repository.${modelNameUpperCamel}Mapper;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional(readOnly=true)
public class ${modelNameUpperCamel}Service extends AbstractService<${modelNameUpperCamel}> {
    // 因为泛型注入，所以这里不必声明mapper的依赖
}
