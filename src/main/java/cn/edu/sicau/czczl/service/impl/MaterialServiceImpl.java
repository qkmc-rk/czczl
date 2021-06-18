package cn.edu.sicau.czczl.service.impl;

import cn.edu.sicau.czczl.entity.Material;
import cn.edu.sicau.czczl.repository.MaterialRepository;
import cn.edu.sicau.czczl.service.MaterialService;
import cn.edu.sicau.czczl.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Override
    public List<Material> findAll() {

        return materialRepository.findAll();
    }

    @Override
    public List<Material> findByJourneyId(Long pointId) {
        List<Material> materialList=materialRepository.findMaterialByJourneyId(pointId);
        return materialList;
    }


}
