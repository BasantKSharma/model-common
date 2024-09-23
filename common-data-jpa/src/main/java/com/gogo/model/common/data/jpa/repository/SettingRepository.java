package com.gogo.model.common.data.jpa.repository;

import com.gogo.model.common.data.jpa.entity.setting.Setting;
import com.gogo.model.common.domain.constants.SettingCategory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Setting entity
 * **/
@Repository
@ConditionalOnProperty(name = "spring.application.name", havingValue = "shopping-service-setting")
public interface SettingRepository extends CrudRepository<Setting, Long> {

    public List<Setting> findByCategory(SettingCategory category);

    @Query("SELECT s FROM Setting s WHERE s.category = ?1 OR s.category = ?2")
    public List<Setting> findByTwoCategories(SettingCategory catOne, SettingCategory catTwo);

    @Query("SELECT s FROM Setting s WHERE s.category IN ?1")
    List<Setting> findByCategories(SettingCategory[] categories);
}
