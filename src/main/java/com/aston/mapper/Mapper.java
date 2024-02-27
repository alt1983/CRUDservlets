package com.aston.mapper;

import com.aston.dto.AuthorDto;
import com.aston.dto.PostDto;
import com.aston.dto.SummaryDto;
import com.aston.entity.Author;
import com.aston.entity.Post;
import com.aston.entity.Summary;

/**
 * @author Mapper layer makes mapping of DTO and Repository layers
 */

public class Mapper {

    public Mapper() {
    }

    public AuthorDto authorDtoMap(Author author) {
        if (author != null) {
            return new AuthorDto(author.getId(), author.getName());
        } else return null;
    }

    public Author authorMap(AuthorDto authorDto) {
        if (authorDto != null) {
            return new Author(authorDto.getId(), authorDto.getName(), true);
        } else return null;
    }

    public SummaryDto summaryDtoMap(Summary summary) {
        if (summary != null) {
            return new SummaryDto(summary.getId(), summary.getPost(), summary.getSummary());
        } else return null;
    }

    public Summary summaryMap(SummaryDto summaryDto) {
        if (summaryDto != null) {
            return new Summary(summaryDto.getId(), summaryDto.getSummary(), summaryDto.getPostid(), true);
        } else return null;
    }

    public PostDto postDtoMap(Post post) {
        if (post != null) {
            return new PostDto(post.getId(), post.getAuthor(), post.getContent());
        } else return null;
    }

    public Post postMap(PostDto postDto) {
        if (postDto != null) {
            return new Post(postDto.getId(), postDto.getAuthorid(), postDto.getContent(), true);
        } else return null;
    }

}
