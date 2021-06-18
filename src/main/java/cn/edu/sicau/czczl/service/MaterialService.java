package cn.edu.sicau.czczl.service;

import cn.edu.sicau.czczl.entity.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialService {
    List<Material> findAll();

    List<Material> findByJourneyId(Long pointId);
}
