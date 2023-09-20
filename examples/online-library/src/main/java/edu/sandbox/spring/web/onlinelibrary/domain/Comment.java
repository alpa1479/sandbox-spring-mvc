package edu.sandbox.spring.web.onlinelibrary.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public Comment(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Comment(Long id, Long bookId, String text) {
        this.id = id;
        this.text = text;
        this.book = new Book(bookId);
    }

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }

    public Comment(Long id, Long bookId, String bookTitle, String text) {
        this.id = id;
        this.text = text;
        this.book = new Book(bookId, bookTitle);
    }

    public Long getBookId() {
        return book.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
