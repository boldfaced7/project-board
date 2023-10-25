package com.project.board.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false, length = 10000)
    private String content; // 본문

    private String hashtag; // 해시태그

    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    public void updateArticle(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    private void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
        userAccount.getArticles().add(this);
    }

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        Article article = new Article(title, content, hashtag);
        article.setUserAccount(userAccount);

        return article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Article that 부분은 Java 16부터 도입된 패턴 변수(Pattern Variable)를 사용한 것으로, o를 Article 타입으로 다운캐스팅하고 그 결과를 that이라는 변수에 바인딩
        if (!(o instanceof Article that)) return false;
        return id != null && id.equals(that.getId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
