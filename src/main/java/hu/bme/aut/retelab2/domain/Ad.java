package hu.bme.aut.retelab2.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
@Entity
public class Ad {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private int price;


    private LocalDateTime publishDate;


    private LocalDateTime closeDate;

    private String secretCode;

    public void setCloseDate(LocalDateTime time){this.closeDate = time;}
    public LocalDateTime getCloseDate(){return closeDate;}

    public void setSecretCode(String code){this.secretCode = code;}
    public String getSecretCode(){return secretCode;}

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId(){return id;}

    public String getTitle(){return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription(){return description;}
    public void setDescription(String description) {this.description = description;}

    public LocalDateTime getPublishDate(){return publishDate;}
    public void setPublishDate(){this.publishDate = LocalDateTime.now();}

    public int getPrice() {return price;}
    public void setPrice(int price){this.price = price;}
}
