package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
        return blogRepository1.findAll();
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {

       Blog blog = new Blog();
        //create a blog at the current time

        User user = userRepository1.findById(userId).get(); //as we need to set user, first get user

        blog.setUser(user);

        blog.setTitle(title);
        blog.setContent(content);

        blog.setPubDate(new Date());

        //updating the blog details
        List<Blog> currBlog = user.getBlogList();
        currBlog.add(blog); //adding the new blog(this blog we created on line 34) in list
        user.setBlogList(currBlog);

        //Updating the userInformation and changing its blogs
        userRepository1.save(user);
    }

    public Blog findBlogById(int blogId){
        //find a blog
     return blogRepository1.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Blog blog = blogRepository1.findById(blogId).get();
        Image image = imageService1.createAndReturn(blog,description,dimensions);
        List<Image> images = blog.getImageList();
        images.add(image);
        blog.setImageList(images);

        blogRepository1.save(blog); //Just calling the parent repository and child repository will automatically be called.

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
       // blogRepository1.deleteById(blogId);

        Blog blog = blogRepository1.findById(blogId).get();
        if(blog != null){
            blogRepository1.delete(blog);
        }

    }
}
