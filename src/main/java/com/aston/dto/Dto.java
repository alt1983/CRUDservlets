package com.aston.dto;

import com.aston.entity.Author;
import com.aston.entity.Post;
import com.aston.entity.Summary;
import com.aston.mapper.Mapper;
import com.aston.repository.AuthorRepository;
import com.aston.repository.PostRepository;
import com.aston.repository.SummaryRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * Data Transfer Object (DTO) layer transfers business objects from Repository to Service layer with Mapper usage
 */


public class Dto {

    private List<PostDto> postsDto;
    private List<AuthorDto> authorsDto;
    private List<SummaryDto> summariesDto;
    private PostRepository postRepository;
    private AuthorRepository authorRepository;
    private SummaryRepository summaryRepository;
    private Mapper mapper;

    public Dto() {
        mapper = new Mapper();
        postRepository = new PostRepository();
        authorRepository = new AuthorRepository();
        summaryRepository = new SummaryRepository();
    }

    public List<PostDto> getPostsDto() {
        postsDto = new ArrayList<>();
        for (Post post : postRepository.getPosts()) {
            postsDto.add(mapper.postDtoMap(post));
        }
        return postsDto;
    }

    public List<SummaryDto> getSummariesDto() {
        summariesDto = new ArrayList<>();
        for (Summary summary : summaryRepository.getSummaries()) {
            summariesDto.add(mapper.summaryDtoMap(summary));
        }
        return summariesDto;
    }

    public List<AuthorDto> getAuthorsDto() {
        authorsDto = new ArrayList<>();
        for (Author author : authorRepository.getAuthors()) {
            authorsDto.add(mapper.authorDtoMap(author));
        }
        return authorsDto;
    }

    public PostDto getById(long id) {
        PostDto postDto = mapper.postDtoMap(postRepository.getById(id));
        return postDto;
    }

    public boolean removeById(long id) {
        return postRepository.removeById(id);
    }

    public boolean put(PostDto postDto) {
        return postRepository.put(mapper.postMap(postDto));
    }

    public PostDto save(PostDto postDto) {
        return mapper.postDtoMap(postRepository.save(mapper.postMap(postDto)));
    }

}
