package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Page_Image")
public class PageImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_image_pk")
    private Long id;
    @Column(name = "image_path")
    private String imagePath;
    @ManyToOne
    @JoinColumn(name = "page_fk", referencedColumnName = "page_pk")
    @ToString.Exclude
    private Page page;
    @Transient
    @JsonIgnore
    @NonNull
    MultipartFile multipartFile;

    @PreRemove
    private void deleteImageFromPage() {
        if (page != null)
            page.deleteImage(this);
    }

}
