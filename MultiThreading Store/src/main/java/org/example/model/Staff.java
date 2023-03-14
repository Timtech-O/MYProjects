package org.example.model;

public abstract class Staff {

    private Long id;
    private String name;
    private String sex;


    public Staff(){}
    public Staff(String name, Long id,  String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;

    }

    public Staff(String name) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +

                '}';
    }
}
