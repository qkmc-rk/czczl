package cn.edu.sicau.czczl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "user")
public class UserEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String university;
  private String college;
  private String major;
  private String grade;
//  修改
  @Column(nullable = false,name = "class")
  private String clazz;
  private String name;
  private String idcard;
  private String stuno;

  @Column(name = "openid")
  private Long openid;
  @Column(name = "score")
  private Long score;
  @Column(name = "add_score")
  private Long addScore;
  @Column(name = "sub_score")
  private Long subScore;
  @Column(name = "step")
  private Long step;
  @Column(name = "shoe")
  private Long shoe;
  @Column(name = "raincoat")
  private Long raincoat;

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
  @Column(name = "is_bind")
  private Boolean isBind;


}
