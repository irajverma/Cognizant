package com.cognizant.ormlearn.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Table(name = "question")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "optionsList")
@EqualsAndHashCode(exclude = "optionsList")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qt_id")
    private int id;

    @Column(name = "qt_text", length = 255, nullable = false)
    private String text;

    @Column(name = "qt_score")
    private double score;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private Set<Options> optionsList;
}
