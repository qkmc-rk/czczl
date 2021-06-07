package cn.edu.sicau.czczl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "journey_point")
public class JourneyPointEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(name = "is_main")
  private Boolean isMain;
  @Column(name = "is_open")
  private Boolean isOpen;

  @Column(name = "parent_id")
  private Long parentId;
  @Column(name = "is_delete")
  private Boolean isDelete;
  private long isHidentask;

  @Column(name = "create_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  @Column(name = "modify_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date modifyTime;



}
