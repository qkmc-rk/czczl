package cn.edu.sicau.czczl.repository;

import cn.edu.sicau.czczl.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MaterialRepository extends JpaRepository<Material, Long> {


    List<Material> findMaterialByJourneyId(Long pointId);
}
