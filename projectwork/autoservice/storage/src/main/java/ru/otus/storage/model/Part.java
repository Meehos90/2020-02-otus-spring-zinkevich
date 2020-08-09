package ru.otus.storage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "storage", name = "part")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String article;

    private String name;

    @Column(name = "auto_mark")
    private String mark;

    @Column(name = "auto_model")
    private String model;

    @Column(name = "range_of_years")
    private String rangeOfYears;

    public Part(String article, String name, String mark, String model, String rangeOfYears) {
        this.article = article;
        this.name = name;
        this.mark = mark;
        this.model = model;
        this.rangeOfYears = rangeOfYears;
    }
}
