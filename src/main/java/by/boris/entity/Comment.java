package by.boris.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(length = 5_000)
    private String text;
    private String country;

    public Comment() {
    }

    public Comment(String text, String country) {
        this.text = text;
        this.country = country;
    }
}
