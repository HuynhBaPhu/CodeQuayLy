package Model;

import java.io.Serializable;

public class SuaBenhNhan implements Serializable {
    private String name;
    private String cmnd;

    public SuaBenhNhan(String name, String cmnd) {
        this.name = name;
        this.cmnd = cmnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }
}
