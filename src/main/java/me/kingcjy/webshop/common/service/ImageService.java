package me.kingcjy.webshop.common.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author KingCjy
 */
@Service
public class ImageService {
    public String uploadImage(MultipartFile multipartFile) {
        return multipartFile.getName();
    }
}
