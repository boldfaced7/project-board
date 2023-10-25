package com.project.board.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class ArticleComment extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article; // 게시글 (ID)

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @Column(nullable = false, length = 500)
    private String content; // 본문

    public void updateContent(String content) {
        this.content = content;
    }


    private ArticleComment(String content) {
        this.content = content;
    }

    private void setArticle(Article article) {
        this.article = article;
        article.getArticleComments().add(this);
    }

    private void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
        userAccount.getArticleComments().add(this);
    }

    public static ArticleComment of(Article article, UserAccount userAccount, String content) {
        ArticleComment articleComment = new ArticleComment(content);
        articleComment.setArticle(article);
        articleComment.setUserAccount(userAccount);

        return articleComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
