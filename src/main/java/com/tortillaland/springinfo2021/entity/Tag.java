package com.tortillaland.springinfo2021.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String nombre;
    @ManyToMany(mappedBy = "tags")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Emprendimieto> emprendimientos = new ArrayList<Emprendimieto>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Emprendimieto> getEmprendimientos() {
        return emprendimientos;
    }
    public void setEmprendimientos(List<Emprendimieto> emprendimientos) {
        this.emprendimientos = emprendimientos;
    }
    @Override
    public String toString() {
        return "Tag [emprendimientos=" + emprendimientos + ", id=" + id + ", nombre=" + nombre + "]";
    }
}