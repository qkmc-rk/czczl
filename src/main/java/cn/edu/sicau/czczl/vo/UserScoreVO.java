package cn.edu.sicau.czczl.vo;

import javax.persistence.Column;

/**
 * 返回用户的积分等信息时使用
 * @author qkmc
 */
public class UserScoreVO {

    private Long id;
    private Integer shoe;
    private Integer raincoat;
    private Integer score;
    private Integer addScore;
    private Integer subScore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getShoe() {
        return shoe;
    }

    public void setShoe(Integer shoe) {
        this.shoe = shoe;
    }

    public Integer getRaincoat() {
        return raincoat;
    }

    public void setRaincoat(Integer raincoat) {
        this.raincoat = raincoat;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getAddScore() {
        return addScore;
    }

    public void setAddScore(Integer addScore) {
        this.addScore = addScore;
    }

    public Integer getSubScore() {
        return subScore;
    }

    public void setSubScore(Integer subScore) {
        this.subScore = subScore;
    }

    @Override
    public String toString() {
        return "UserScoreVO{" +
                "id=" + id +
                ", shoe=" + shoe +
                ", raincoat=" + raincoat +
                ", score=" + score +
                ", addScore=" + addScore +
                ", subScore=" + subScore +
                '}';
    }
}
