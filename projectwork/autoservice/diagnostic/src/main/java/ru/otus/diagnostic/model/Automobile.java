package ru.otus.diagnostic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "diagnostic", name = "automobile")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
