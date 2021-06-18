package cn.edu.sicau.czczl.repository;

import cn.edu.sicau.czczl.entity.ExamItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamItemRepository extends JpaRepository<ExamItem,Integer> {

    List<ExamItem> findExamItemByMaterialId(Long materialid);
}
