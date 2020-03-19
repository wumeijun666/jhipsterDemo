package com.mycompany.myapp.domain;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Bird.
 */
@Entity
@Table(name = "bird")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bird implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "jhi_add", length = 100, nullable = false)
    private String add;

    @NotNull
    @Min(value = 18)
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name="clazz_test")
    private String clazzTest;

    @NotNull
    @Column(name="seven", nullable = false)
    private Integer seven;

    public Integer getSeven() {
        return seven;
    }

    public void setSeven(Integer seven) {
        this.seven = seven;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Bird name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public Bird add(String add) {
        this.add = add;
        return this;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public Integer getAge() {
        return age;
    }

    public Bird age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public String getClazzTest() {
        return clazzTest;
    }

    public void setClazzTest(String clazzTest) {
        this.clazzTest = clazzTest;
    }

    @Override
    public String toString() {
        return "Bird{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", add='" + add + '\'' +
            ", age=" + age +
            ", clazzTest='" + clazzTest + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Bird)) {
            return false;
        }
        return id != null && id.equals(((Bird) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
