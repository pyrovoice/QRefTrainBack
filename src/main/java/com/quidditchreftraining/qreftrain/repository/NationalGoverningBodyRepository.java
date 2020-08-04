package com.quidditchreftraining.qreftrain.repository;

import com.quidditchreftraining.qreftrain.dao.NationalGoverningBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface NationalGoverningBodyRepository extends JpaRepository<NationalGoverningBody, Integer> {
    NationalGoverningBody findOneByAbbreviation(String abbreviation);
}
