package com.pblgllgs.datajpa.repository;

import com.pblgllgs.datajpa.entity.StudentIdCad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentIdCardRepository extends CrudRepository<StudentIdCad,Long> {
}
