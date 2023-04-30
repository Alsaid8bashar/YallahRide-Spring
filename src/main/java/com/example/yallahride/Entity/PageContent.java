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
@Table(name = "Page_Content")
public class PageContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_content_pk")
    private Long id;
    @Column(name = "content")
    @NonNull
    String content;
    @ManyToOne(optional = false)
    @JoinColumn(name = "page_fk", referencedColumnName = "page_pk")
    private Page page;

}
