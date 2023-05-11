package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Page_Image")
public class PageImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_image_pk")
    private Long id;
    @Column(name = "image_path")
    @NonNull
    private String imagePath;
    @ManyToOne
    @JoinColumn(name = "page_fk", referencedColumnName = "page_pk")
    @ToString.Exclude
    private Page page;

    @PreRemove
    private void deleteImageFromPage() {
        if (page != null)
            page.deleteImage(this);
    }

}
