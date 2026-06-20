package com.cognizant.ormlearn.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Table(name = "attempt_question")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "attemptOptions")
@EqualsAndHashCode(exclude = "attemptOptions")
public class AttemptQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aq_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "aq_at_id", nullable = false)
    private Attempt attempt;

    @ManyToOne
    @JoinColumn(name = "aq_qt_id", nullable = false)
    private Question question;

    @OneToMany(mappedBy = "attemptQuestion", fetch = FetchType.LAZY)
    private Set<AttemptOption> attemptOptions;
}
