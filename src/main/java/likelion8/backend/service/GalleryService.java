package likelion8.backend.service;

import jakarta.transaction.Transactional;
import likelion8.backend.domain.Gallery;
import likelion8.backend.dto.GalleryRequestDto;
import likelion8.backend.dto.GalleryResponseDto;
import likelion8.backend.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GalleryService {

    private final GalleryRepository galleryRepository;

    //포스트한 갤러리 목록 조회
    @Transactional
    public List<GalleryResponseDto> getAllGalleries(){
        List<Gallery> galleries = galleryRepository.findAll();
        return galleries.stream().map(
                gallery -> new GalleryResponseDto(gallery)
        ).toList();
    }

    //프론트에서 데이터 보낼때
    @Transactional
    public void createGallery(GalleryRequestDto dto, String imageUrl){
        Gallery gallery = new Gallery(dto);
        gallery.setImage(imageUrl);
        galleryRepository.save(gallery);
    }

//    //갤러리 등록
//    public void saveGallery(Gallery gallery){
//        galleryRepository.save(gallery);
//    }

//    //아이디로 갤러리 찾기
//    public Gallery getGalleryByID(Long id){
//        return galleryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("해당 게시물이 존재하지 않습니다."));
//    }

    //갤러리 수정
    public GalleryResponseDto updateGallery(GalleryRequestDto dto, Long galleryId) {
        Gallery gallery = galleryRepository.findById(galleryId)
                .orElseThrow(() -> new RuntimeException("해당 아이디 게시물 없음"+galleryId));
        gallery.update(dto);
        return new GalleryResponseDto(gallery);
    }

    //삭제
//    public void deleteGallery(Long id){
//        Gallery deleteGallery = galleryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("찾을 수 없음"));
//
//        galleryRepository.deleteById(id);
//    }
    @Transactional
    public void deleteGallery(Long galleryId){
        if (!galleryRepository.existsById(galleryId)){
            throw new RuntimeException("그런 게시물 없음"+galleryId);
        }
        galleryRepository.deleteById(galleryId);
    }



}
