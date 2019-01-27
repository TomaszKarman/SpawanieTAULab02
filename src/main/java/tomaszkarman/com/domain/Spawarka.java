package tomaszkarman.com.domain;

public class Spawarka {
    public int id;
    public String name;
    public String model;
    public Integer kod;

    public Spawarka(String name, String model, Integer kod) {
        this.name = name;
        this.model = model;
        this.kod = kod;
    }

    public Spawarka() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getKod() {
        return kod;
    }

    public void setKod(Integer kod) {
        this.kod = kod;
    }
}

