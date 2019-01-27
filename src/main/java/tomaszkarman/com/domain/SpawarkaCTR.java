package tomaszkarman.com.domain;

import java.util.Date;

public class SpawarkaCTR extends Spawarka {

    Date createTime;
    Date readTime;
    Date modifyTime;

    public SpawarkaCTR() {
    }

    public SpawarkaCTR(String name, String model, Integer kod) {
        super(name, model, kod);
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getReadTime() {
        return readTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }
}
