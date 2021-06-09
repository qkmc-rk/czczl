package cn.edu.sicau.czczl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String university;
  private String college;
  private String major;
  private String grade;
  private String clazz;
  private String name;
  private String idcard;
  private String stuno;

  @Column(name = "openid")
  private String openid;
  @Column(name = "score")
  private Integer score;
  @Column(name = "add_score")
  private Integer addScore;
  @Column(name = "sub_score")
  private Integer subScore;
  @Column(name = "step")
  private Integer step;
  @Column(name = "shoe")
  private Integer shoe;
  @Column(name = "raincoat")
  private Integer raincoat;

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
