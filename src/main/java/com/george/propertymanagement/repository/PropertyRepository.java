package com.george.propertymanagement.repository;
import com.george.propertymanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

    List<PropertyEntity> findAllByUserEntityId(Long userId);
}
