package com.example.yallahride.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Page_Video")
public class PageVideo {

    @Transient
    @JsonIgnore
    @NonNull
    MultipartFile multipartFile;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_video_pk")
    private Long id;
    @Column(name = "video_path")
    private String videoPath;
    @ManyToOne
    @JoinColumn(name = "page_fk", referencedColumnName = "page_pk")
    @ToString.Exclude
    private Page page;

    @PreRemove
    private void deleteVideoFromPage() {
        if (page != null)
            page.deleteVideo(this);
    }
}
