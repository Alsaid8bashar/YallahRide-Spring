package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Page")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_pk")
    private Long id;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="page_content_pk")
    Set<PageContent> pageContentSet;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="page_image_pk")
    Set<PageImage> pageImageSet;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="page_video_pk")
    Set<PageVideo> pageVideoSet;
}
