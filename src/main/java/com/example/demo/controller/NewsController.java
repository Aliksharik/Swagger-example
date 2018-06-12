package com.example.demo.controller;

import com.example.demo.model.News;
import com.example.demo.beans.NewsRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    /**
     * Create new news
     * @param news Created news
     * @return  Current news
     */
    @PostMapping(value = "news" , produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("${newcontroller.addNews}")
    public @ResponseBody News addNews(@ApiParam("News information for a new news to be created. ") @RequestBody News news) throws Exception {
        return newsRepository.save(news);
    }

    /**
     * Search news by key or cityId
     * @param keyword It must like title or content
     * @param cityId Id of the city
     * @return List of news
     */
    @GetMapping(value = "news/search" , produces = {"application/json"})
    @ApiOperation("${newcontroller.searchNews}")
    public @ResponseBody Page<News> searchNews( @ApiParam("Keyword that will be searched in the title or content") @RequestParam(value = "keyword" ,required = false)  String keyword,
                                                @ApiParam("Id of the city")@RequestParam(value = "cityId" ,required = false)  Long cityId, Pageable pageable) throws Exception{
        Page<News> news = null;

        if(cityId == null)
            news = newsRepository.findByTitleContent(keyword, pageable);
        else if( keyword == null )
            news = newsRepository.findByCityId(cityId, pageable);
        else
            news = newsRepository.findByTitleContentCity(keyword, cityId, pageable);

        return news;
    }

    /**
     * Displays current news
     * @param id Id of News
     * @return Type News
     */
    @ApiOperation("${newcontroller.getNews}")
    @GetMapping(value = "news/{id}" , produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody News getNews(@ApiParam("Id of the person to be obtained. Cannot be empty.") @PathVariable("id") Long id) throws Exception{
        return newsRepository.findById(id).get();
    }


    /**
     * Update current news
     * @param id Id of News
     * @param news News that will be updated
     * @return News
     */
    @ApiOperation("${newcontroller.updateNews}")
    @PutMapping(value = "news/{id}" , produces = {"application/json"} )
    @ExceptionHandler
    public @ResponseBody News updateNews(@ApiParam("Id of the person to be updated. Cannot be empty.") @PathVariable Long id , @RequestBody News news ) throws Exception{
        News currentNews =  newsRepository.findById(id).get();

        currentNews.setcityId(news.getcityId());
        currentNews.setCreated(news.getCreated());
        currentNews.setAuthor(news.getAuthor());
        currentNews.setTitle(news.getTitle());
        currentNews.setContent(news.getContent());
        currentNews.setDeleted(news.getDeleted());
        currentNews.setforTeacher(news.getforTeacher());
        currentNews.setPhoto(news.getPhoto());

        return newsRepository.save(currentNews);
    }

}
