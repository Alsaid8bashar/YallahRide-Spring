package com.example.yallahride.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Transactional
@Table(name = "Page_Content")
public class PageContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_content_pk")
    private Long id;
    @Column(name = "content")
    @NonNull
    private String content;
    @ManyToOne
    @JoinColumn(name = "page_fk", referencedColumnName = "page_pk")
    @ToString.Exclude
    private Page page;

    @PreRemove
    private void deleteContentFromPage() {
        page.deleteContent(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageContent that)) return false;
        return Objects.equals(getId(), that.getId()) && getContent().equals(that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent());
    }
}
