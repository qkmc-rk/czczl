package cn.edu.sicau.czczl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "question_record")
public class QuestionRecord implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "point_id")
  private Long pointId;

  @Column(name = "question_id")
  private Long questionId;

  @Column(name = "is_answer")
  private Boolean isAnswer;

  @Column(name = "q1")
  private Boolean q1;

  @Column(name = "q2")
  private Boolean q2;

  @Column(name = "q3")
  private Boolean q3;

  @Column(name = "q4")
  private Boolean q4;

  @Column(name = "q5")
  private Boolean q5;

  @Column(name = "q6")
  private Boolean q6;

  @Column(name = "q7")
  private Boolean q7;

  @Column(name = "q8")
  private Boolean q8;

  @Column(name = "q9")
  private Boolean q9;

  @Column(name = "q10")
  private Boolean q10;

  @Column(name = "create_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  @Column(name = "modify_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date modifyTime;
  @Column(name = "is_delete")
  private Boolean isDelete = false;



}
