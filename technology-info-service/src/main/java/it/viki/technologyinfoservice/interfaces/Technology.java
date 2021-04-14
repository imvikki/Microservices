package it.viki.technologyinfoservice.interfaces;

public class Technology {

    private String technoId;
    private String technoName;

    public Technology(String technoId, String technoName) {
        this.technoId = technoId;
        this.technoName = technoName;
    }

    public String getTechnoId() {
        return technoId;
    }

    public void setTechnoId(String technoId) {
        this.technoId = technoId;
    }

    public String getTechnoName() {
        return technoName;
    }

    public void setTechnoName(String technoName) {
        this.technoName = technoName;
    }
}
