package com.arnab.springboot.learning.springboot_learning.models;

public class Employee {
    private Long id;
    private String name;
    private String city;

    public Employee() {
    }

    public Employee(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "(" + id + ", " + name.toUpperCase() + ", " + city.toUpperCase() + ")";
    }
}
