package likelion8.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class GalleryResponseDto {

    private Long id;
    private byte[] image;
    private String title;
    private String description;
    private LocalDateTime lastUpdate;

}
