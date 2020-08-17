package ru.otus.diagnostic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "diagnostic", name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "parts_and_count")
    private String partsAndCount;

    @Column(name = "job_time")
    private LocalDateTime jobTime;

    public Order(String partsAndCount, LocalDateTime jobTime) {
        this.partsAndCount = partsAndCount;
        this.jobTime = jobTime;
    }

}
