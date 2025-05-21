package likelion8.backend.controller;

import likelion8.backend.domain.Gallery;
import likelion8.backend.dto.GalleryResponseDto;
import likelion8.backend.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/galleries")
public class GalleryController {

    private final GalleryService galleryService;

    // GET: api/galleries
    @GetMapping()
    public ResponseEntity<List<GalleryResponseDto>> getAllGalleries(){
        List<Gallery> galleries = galleryService.getAllGalleries();
        return new ResponseEntity<>(galleries.stream()
                .map(g -> new GalleryResponseDto(
                        g.getId(),
                        g.getImage(),
                        g.getTitle(),
                        g.getDescription(),
                        g.getLastUpdate()
                ))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    // Post: api/galleries
    @PostMapping("/post")
    public ResponseEntity<Void> postGallery(
            @RequestParam("image") MultipartFile image,
            @RequestParam("title") String title,
            @RequestParam("description") String description) {

        try{
            byte[] imageBytes = image.getBytes();

            Gallery gallery = new Gallery(null, imageBytes, title, description, LocalDateTime.now());
            galleryService.saveGallery(gallery);

            return ResponseEntity.ok().build();
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET: api/galleries/{galleryId}
    @GetMapping("/{galleryId}")
    public ResponseEntity<GalleryResponseDto> getGalleryById(@PathVariable Long galleryId){
        Gallery gallery = galleryService.getGalleryByID(galleryId);

        GalleryResponseDto dto = new GalleryResponseDto(
                gallery.getId(),
                gallery.getImage(),
                gallery.getTitle(),
                gallery.getTitle(),
                gallery.getLastUpdate()
        );

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // PATCH: /api/galleries/{galleryId}
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateGallery(
            @PathVariable Long id,
            @RequestParam(value = "image") MultipartFile image,
            @RequestParam("title") String title,
            @RequestParam("description") String description){

        try{
            byte[] imageBytes = image != null ? image.getBytes() : null;

            GalleryResponseDto dto = new GalleryResponseDto(
                    id,
                    imageBytes,
                    title,
                    description,
                    LocalDateTime.now()
            );
            galleryService.updateGallery(id, dto);

            return ResponseEntity.ok("수정완료");
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류");
        }
    }

    // DELETE: api/galleries/{galleryId}
    @DeleteMapping("/{galleryId}")
    public ResponseEntity<String> deleteGallery(@PathVariable Long galleryId){
        galleryService.deleteGallery(galleryId);

        return ResponseEntity.ok("삭제완료");
    }

}
