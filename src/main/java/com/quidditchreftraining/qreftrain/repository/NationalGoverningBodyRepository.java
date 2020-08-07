package com.quidditchreftraining.qreftrain.repository;

import com.quidditchreftraining.qreftrain.dao.NationalGoverningBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public interface NationalGoverningBodyRepository extends JpaRepository<NationalGoverningBody, Integer> {
    NationalGoverningBody findOneByAbbreviation(String abbreviation);
    Collection<NationalGoverningBody> findByAbbreviationIn(Collection<String> abbreviations);
}
