package cn.edu.sicau.czczl.service.impl;

import cn.edu.sicau.czczl.entity.JourneyPoint;
import cn.edu.sicau.czczl.repository.JourneyPointRepository;
import cn.edu.sicau.czczl.service.JourneyPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JourneyPointServiceImpl implements JourneyPointService {

    @Autowired
    private JourneyPointRepository journeyPointRepository;

    @Override
    public Optional<JourneyPoint> findByPointId(Long pointId) {
        Optional<JourneyPoint> journeyPoint = journeyPointRepository.findById(pointId);
        return journeyPoint;
    }
}
