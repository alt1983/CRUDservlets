package com.aston;

import com.aston.dto.AuthorDto;
import com.aston.dto.Dto;
import com.aston.dto.PostDto;
import com.aston.dto.SummaryDto;
import com.aston.entity.Author;
import com.aston.entity.Post;
import com.aston.entity.Summary;
import com.aston.mapper.Mapper;
import com.aston.repository.AuthorRepository;
import com.aston.repository.PostRepository;
import com.aston.repository.SummaryRepository;
import com.aston.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ServletsTests {

    Dto dto = Mockito.mock(Dto.class);
    PostService postService = new PostService(dto);
    Mapper mapper = new Mapper();

    List<Author> authors = new ArrayList<>();
    AuthorRepository authorRepository = new AuthorRepository(authors);

    List<Summary> summaries = new ArrayList<>();
    SummaryRepository summaryRepository = new SummaryRepository(summaries);
    List<Post> posts = new ArrayList<>();
    PostRepository postRepository = new PostRepository(posts);

    @Test
    void allPostServiceTest(){
        List<PostDto> postDtos = new ArrayList<>();
        postDtos.add(new PostDto());
        Mockito.when(dto.getPostsDto()).thenReturn(postDtos);
        Assertions.assertNotNull(postService.all());
        Assertions.assertEquals(postService.all().getClass(), postDtos.getClass());
    }

    @Test
    void getByIdPostServiceTest(){
        PostDto postDto = new PostDto(1l,1l,"1");
        Mockito.when(dto.getById(1)).thenReturn(postDto);
        Assertions.assertNotNull(postService.getById(1));
        Assertions.assertEquals(postService.getById(1), postDto);
    }

    @Test
    void savePostServiceTest(){
        PostDto postDtoToSave = new PostDto(0l,1l,"1");
        PostDto postDtoSaved = new PostDto(1l,1l,"1");
        Mockito.when(dto.save(postDtoToSave)).thenReturn(postDtoSaved);
        Assertions.assertNotNull(postService.save(postDtoToSave));
        Assertions.assertEquals(postService.save(postDtoToSave).getClass(), postDtoSaved.getClass());
    }

    @Test
    void putPostServiceTest(){
        PostDto postDto = new PostDto(1l,1l,"1");
        Mockito.when(dto.put(postDto)).thenReturn(true);
        Assertions.assertTrue(postService.put(postDto));
    }

    @Test
    void removedByIdPostServiceTest(){
        long id = 1l;
        Mockito.when(dto.removeById(id)).thenReturn(true);
        Assertions.assertTrue(postService.removeById(id));
    }

    @Test
    void authorDtoMapMapperTest(){
        Author author = new Author(1l,"name",true);
        Assertions.assertNull(mapper.authorDtoMap(null));
        Assertions.assertEquals(mapper.authorDtoMap(author).getClass(), AuthorDto.class);
    }

    @Test
    void authorMapMapperTest(){
        AuthorDto authorDto = new AuthorDto(1l,"name");
        Assertions.assertNull(mapper.authorMap(null));
        Assertions.assertEquals(mapper.authorMap(authorDto).getClass(), Author.class);
    }

    @Test
    void summaryDtoMapMapperTest(){
        Summary summary = new Summary(1l,"summary",1l,true);
        Assertions.assertNull(mapper.summaryDtoMap(null));
        Assertions.assertEquals(mapper.summaryDtoMap(summary).getClass(), SummaryDto.class);
    }

    @Test
    void summaryMapMapperTest(){
        SummaryDto summaryDto = new SummaryDto(1l,1l,"summary");
        Assertions.assertNull(mapper.summaryMap(null));
        Assertions.assertEquals(mapper.summaryMap(summaryDto).getClass(), Summary.class);
    }

    @Test
    void postDtoMapMapperTest(){
        Post post = new Post(1l,1l,"post",true);
        Assertions.assertNull(mapper.postDtoMap(null));
        Assertions.assertEquals(mapper.postDtoMap(post).getClass(), PostDto.class);
    }

    @Test
    void postMapMapperTest(){
        PostDto postDto = new PostDto(1l,1l,"post");
        Assertions.assertNull(mapper.postMap(null));
        Assertions.assertEquals(mapper.postMap(postDto).getClass(), Post.class);
    }

    @Test
    void getSummariesSummaryRepository(){
        summaries.add(new Summary(1l,"summary",1l,true));
        Assertions.assertNotNull(summaryRepository.getSummaries());
        Assertions.assertEquals(summaryRepository.getSummaries().get(0), summaries.get(0));
        summaries.clear();
    }

    @Test
    void getAuthorsAuthorRepository(){
        authors.add(new Author(1l,"name",true));
        Assertions.assertNotNull(authorRepository.getAuthors());
        Assertions.assertEquals(authorRepository.getAuthors().get(0), authors.get(0));
        authors.clear();
    }

    @Test
    void getPostsPostRepository(){
        posts.add(new Post(1l,1l,"post",true));
        Assertions.assertNotNull(postRepository.getPosts());
        Assertions.assertEquals(postRepository.getPosts().get(0), posts.get(0));
        posts.clear();
    }

    @Test
    void getByIdsPostRepository(){
        long id = 1l;
        Post post = new Post(id,1l,"post",true);
        posts.add(post);
        Assertions.assertNotNull(postRepository.getById(id));
        Assertions.assertEquals(postRepository.getById(id), post);
        posts.clear();
    }

    @Test
    void putPostRepository(){
        long id = 1l;
        Post postForUpdate = new Post(id,1l,"post",true);
        Post postUpdated = new Post(id,1l,"new post",true);
        posts.add(postForUpdate);
        Assertions.assertTrue(postRepository.put(postUpdated));
        posts.clear();
    }

    @Test
    void removeByIdPostRepository(){
        long id = 1l;
        Post post = new Post(id,1l,"post",true);
        posts.add(post);
        Assertions.assertTrue(postRepository.removeById(1l));
        posts.clear();
    }

}
