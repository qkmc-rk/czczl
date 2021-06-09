package cn.edu.sicau.czczl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "exam_item")
public class ExamItem implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String stem;
  @Column(name = "material_id")
  private Long materialId;
  @Column(name = "stem_type")
  private Integer stemType;
  private String choiceA;
  private String choiceB;
  private String choiceC;
  private String choiceD;
  private String choiceAnswer;
  private String judgeAnswer;
  private String fillAnswer;
  @Column(name = "create_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  @Column(name = "modify_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date modifyTime;

  @Column(name = "is_delete")
  private Boolean isDelete;
  @Column(name = "is_prize_raincoat")
  private Boolean isPrizeRaincoat;


}
