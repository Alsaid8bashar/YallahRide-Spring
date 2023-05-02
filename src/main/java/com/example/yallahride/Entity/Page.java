package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Page")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_pk")
    private Long id;


    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PageContent> pageContentSet = new HashSet<>();
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PageImage> pageImageSet = new HashSet<>();
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PageVideo> pageVideoSet = new HashSet<>();


    public void addContent(PageContent pageContent) {
        pageContentSet.add(pageContent);
    }

    public void addImage(PageImage pageImage) {
        pageImageSet.add(pageImage);
    }

    public void addVideo(PageVideo pageVideo) {
        pageVideoSet.add(pageVideo);
    }

    public void deleteContent(PageContent pageContent) {
        pageContentSet.remove(pageContent);
    }

    public void deleteImage(PageImage pageImage) {
        pageImageSet.remove(pageImage);
    }

    public void deleteVideo(PageVideo pageVideo) {
        pageVideoSet.remove(pageVideo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page page)) return false;
        return getId().equals(page.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
