package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;
    @Autowired
    private BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image();

        image.setBlog(blog);
        image.setDescription(description);
        image.setDimensions(dimensions);
 //set List<image>as its there in blog due to bidirectional relation add this image and update
        List<Image> currImage =   blog.getImageList();  //all List<image>in curr image
        currImage.add(image); //adding this image class we created and updated on and after line 17
        blog.setImageList(currImage);

        blogRepository.save(blog); //image list updated,so need to save blog,and it eill automtically save image
        return image;
    }

    public void deleteImage(Image image){
    imageRepository2.delete(image);
    }

    public Image findById(int id) {
        Image image = imageRepository2.findById(id).get();
        return image;
    }

//    public int countImagesInScreen(Image image, String screenDimensions) {
//        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
//        //In case the image is null, return 0
//
//    }
}
