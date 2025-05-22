package likelion8.backend.dto;


import likelion8.backend.domain.Gallery;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class GalleryResponseDto {

    private Long id;
    private String image;
    private String title;
    private String description;
    private LocalDateTime lastUpdate;

    public GalleryResponseDto(Gallery gallery) {
        this.id = gallery.getId();
        this.image = gallery.getImage();
        this.title = gallery.getTitle();
        this.description = gallery.getDescription();
    }
}
