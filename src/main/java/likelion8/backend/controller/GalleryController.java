package likelion8.backend.controller;

import likelion8.backend.domain.Gallery;
import likelion8.backend.dto.GalleryRequestDto;
import likelion8.backend.dto.GalleryResponseDto;
import likelion8.backend.service.GalleryService;
import likelion8.backend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/galleries")
public class GalleryController {

    private final GalleryService galleryService;
    private final ImageService imageService;

    // GET: api/galleries
    @GetMapping
    public ResponseEntity<List<GalleryResponseDto>> getAllGalleries(){
        return ResponseEntity.ok(galleryService.getAllGalleries());
    }

    // Post: api/galleries
    @PostMapping
    public ResponseEntity<Void> postGallery(
            @RequestPart("image") MultipartFile image,
            @RequestPart("data") GalleryRequestDto data
    ){
        String imageUrl = "";
        try{
            imageUrl = imageService.saveImageGetUrl(image);
        } catch (IOException e){
            throw new RuntimeException("이미지 업로드 과정에서 문제가 생겼습니다.");
        }

        galleryService.createGallery(data, imageUrl);

        // 상태코드: 201 Created, response body는 없습니다!
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    // PUT: /api/galleries/{galleryId}
    @PutMapping("/{galleryId}")
    public ResponseEntity<GalleryResponseDto> postGallery(
        @RequestBody GalleryRequestDto dto,
        @PathVariable Long galleryId
    ){
        GalleryResponseDto res = galleryService.updateGallery(dto, galleryId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    // DELETE: api/galleries/{galleryId}
    @DeleteMapping("/{galleryId}")
    public ResponseEntity<Void> deleteGallery(@PathVariable Long galleryId) {
        galleryService.deleteGallery(galleryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //GET: api/galleries/{galleryId}
    @GetMapping("/{galleryId}")
    public ResponseEntity<GalleryResponseDto> getGalleryById(@PathVariable Long galleryId){
        return ResponseEntity.ok(galleryService.getGalleryById(galleryId));
    }

}
