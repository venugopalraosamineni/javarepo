package com.altron.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altron.api.entity.SampleMessageEntity;


@Repository
public interface SampleMessageEntityRepository  extends CrudRepository<SampleMessageEntity, Long>{

}
