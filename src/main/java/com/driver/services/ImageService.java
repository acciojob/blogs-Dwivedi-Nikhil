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
//    @Autowired
//     BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image();

        image.setBlog(blog);
        image.setDescription(description);
        image.setDimensions(dimensions);
 //set List<image>as its there in blog due to bidirectional relation add this image and update
//        List<Image> currImage =   blog.getImageList();  //all List<image>in curr image
//        currImage.add(image); //adding this image class we created and updated on and after line 17
//        blog.setImageList(currImage);

        imageRepository2.save(image);
        //blogRepository.save(blog); //image list updated,so need to save blog,and it eill automtically save image
        return image;
    }

    public void deleteImage(Image image){

    imageRepository2.delete(image);
    }

    public Image findById(int id) {
//        Image image = imageRepository2.findById(id).get();
//        return image;
        return imageRepository2.findById(id).get();  //by sir
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        String dimensions = image.getDimensions();
        int xi = 0;
        int yi = 0;
        int xs = 0;
        int ys = 0;
        int num = 0;
        for(int i = 0; i<dimensions.length(); i++){
            if(dimensions.charAt(i) == 'X'){
                xi = num;
                num = 0;
                continue;
            }
            num *= 10;
            num += (dimensions.charAt(i) - '0');
        }
        yi = num;
        num = 0;
        for(int i = 0; i<screenDimensions.length(); i++){
            if(screenDimensions.charAt(i) == 'X'){
                xs = num;
                num = 0;
                continue;
            }
            num *= 10;
            num += (screenDimensions.charAt(i) - '0');
        }
        ys = num;

        int ans = (int) (Math.floor((new Double(xs))/(new Double(xi))) * Math.floor((new Double(ys))/(new Double(yi))));
        return ans;


    }
}
