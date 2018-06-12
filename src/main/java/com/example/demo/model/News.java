package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="news1")
public class News {

    /**
     * Id of the news
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @ApiModelProperty(notes = "${news.id}", example = "1", required = true, position = 0)
    private Long id;

    /**
     * City id
     */
    @Column(name = "city_id")
    @Max(50)
    @NonNull
    @ApiModelProperty(notes = "${news.cityId}", example = "1", required = true, position = 1)
    private Long cityId;

    /**
     * Created day
     */

    @Column(name = "created")
    @NonNull
    @ApiModelProperty(notes = "${news.created}", example = "2018-06-12T18:00:00.000+0000", required = true, position = 2)
    private Date created;


    /**
     * The news author
     */
    @Column(name = "author")
    @NonNull
    @ApiModelProperty(notes = "${news.author}", example = "0", required = true, position = 3)
    private Long author;

    /**
     *  Title of news
     */
    @Column(name = "title")
    @Size(min = 3 , max = 100)
    @NotNull
    @ApiModelProperty(notes = "${news.title}", example = "Школа \"Пифагор\"", required = true, position = 4)
    private String title;

    /**
     * The content of news
     */
    @Column(name = "content")
    @NonNull
    @ApiModelProperty(notes = "${news.content}", example = "Сегодня ...", required = true, position = 5)
    private String content;

    /**
     * News is deleted
     */
    @Column(name = "deleted")
    @ApiModelProperty(notes = "${news.deleted}", example = "0", required = true, position = 6)
    private int deleted;

    /**
     * The teacher who received the news
     */
    @Column(name = "for_teacher")
    @ApiModelProperty(notes = "${news.forTeacher}", example = "0", required = true, position = 7)
    private Long forTeacher;

    /**
     * Photo of the news
     */
    @Column(name = "photo")
    @ApiModelProperty(notes = "${news.photo}", example = "no_photo.jpg", required = true, position = 8)
    private String photo;

    public News(){}

    public News(Long cityId, Date created, Long author, String title, String content, int deleted, Long forTeacher, String photo) {
        this.cityId = cityId;
        this.created = created;
        this.author = author;
        this.title = title;
        this.content = content;
        this.deleted = deleted;
        this.forTeacher = forTeacher;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getcityId() {
        return cityId;
    }

    public void setcityId(Long cityId) {
        this.cityId = cityId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Long getforTeacher() {
        return forTeacher;
    }

    public void setforTeacher(Long forTeacher) {
        this.forTeacher = forTeacher;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
