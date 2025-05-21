package likelion8.backend.service;

import jakarta.transaction.Transactional;
import likelion8.backend.domain.Gallery;
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
    public List<Gallery> getAllGalleries(){
        return galleryRepository.findAll();
    }

    //갤러리 등록
    public void saveGallery(Gallery gallery){
        galleryRepository.save(gallery);
    }

    //아이디로 갤러리 찾기
    public Gallery getGalleryByID(Long id){
        return galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시물이 존재하지 않습니다."));
    }

    //갤러리 수정
    public void updateGallery(Long id, GalleryResponseDto dto) {
        Gallery updateGallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("찾을 수 없음"));

        updateGallery.update(
                dto.getImage(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getLastUpdate()
        );
    }

    //삭제
    public void deleteGallery(Long id){
        Gallery deleteGallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("찾을 수 없음"));

        galleryRepository.deleteById(id);
    }



}
