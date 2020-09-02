package ru.otus.storage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_type_id")
    private PartType partType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mark_id")
    private Mark mark;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "range_of_years_id")
    private YearsRange rangeOfYears;

    public Part(String article, PartType partType, Mark mark, Model model, YearsRange rangeOfYears) {
        this.article = article;
        this.partType = partType;
        this.mark = mark;
        this.model = model;
        this.rangeOfYears = rangeOfYears;
    }
}
