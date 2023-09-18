package hu.bme.aut.retelab2.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class Ad {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private int price;

    private LocalDateTime publishDate;

    public void setPublishDate(){
        this.publishDate = LocalDateTime.now();
    }
}
