package cn.edu.sicau.czczl.service;

import cn.edu.sicau.czczl.entity.JourneyPoint;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface JourneyPointService {
    Optional<JourneyPoint> findByPointId(Long pointId);
}
