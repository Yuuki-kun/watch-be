package com.securityjwt.demojwt.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "watch")
public class Watch{
    @Id
    @GeneratedValue
    private Long id;

    private String reference;
    private String name;
    private LocalDate produced;
    private String origin;
    private float weight;
    private String gender;
    private long warranty;
    private boolean limited;
    @Column(length = 1000)
    private String description;
    private long inventoryQuantity;
    private long soldQuantity;
    private float defaultPrices;
    private float stars;

    //watch as association owner
    @ManyToOne
    @JoinColumn(name = "brand_id")
//    @JsonBackReference
    @JsonManagedReference //=> when to json this Brand will serialize
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "family_id")
//    @JsonBackReference
    @JsonManagedReference //=> when to json this Family will serialize
    private Family family;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    @OneToMany(mappedBy = "watch", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Dial> dials = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "wcase_id")
    private Case watchCase;

    @ManyToOne
    @JoinColumn(name = "movement_id")
    private Movement movement;

    @OneToMany(mappedBy = "watch", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "watch", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

    public void setImages(List<Image> images) {
        this.images = images;
        for (Image i: images) {
            i.setWatch(this);
        }
    }

    public void removeImage(Image image){
        this.images.remove(image);
        image.setWatch(null);
    }

    public void setDials(List<Dial> dials) {
        this.dials = dials;
        for (Dial d: dials) {
            d.setWatch(this);
        }
    }

    public void removeDial(Dial dial){
        this.dials.remove(dial);
        dial.setWatch(null);
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        for (Review r: reviews) {
            r.setWatch(this);
        }
    }
    public void addReview(Review r){
        this.reviews.add(r);
        r.setWatch(this);
    }

    public void removeReview(Review r){
        this.reviews.remove(r);
        r.setWatch(null);
    }


}
