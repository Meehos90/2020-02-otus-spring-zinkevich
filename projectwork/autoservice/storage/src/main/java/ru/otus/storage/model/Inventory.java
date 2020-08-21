package ru.otus.storage.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "storage", name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_id")
    private Part part;

    private Integer count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(name = "in_order")
    private boolean inOrder = false;

    public Inventory(Part part, Integer count, Place place) {
        this.part = part;
        this.count = count;
        this.place = place;
    }

    public Inventory(Part part, Integer count, Place place, boolean inOrder) {
        this.part = part;
        this.count = count;
        this.place = place;
        this.inOrder = inOrder;
    }
}
