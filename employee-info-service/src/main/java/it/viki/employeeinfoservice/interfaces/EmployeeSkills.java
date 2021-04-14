package it.viki.employeeinfoservice.interfaces;

public class EmployeeSkills {

    final String empSkillId;
    final String technology;
    final String rating;

    public EmployeeSkills(String empSkillId, String technology, String rating) {
        this.empSkillId = empSkillId;
        this.technology = technology;
        this.rating = rating;
    }

    public String getEmpSkillId() {
        return empSkillId;
    }

    public String getTechnology() {
        return technology;
    }

    public String getRating() {
        return rating;
    }
}
