package br.com.zupacademy.polyana.mercadolivre.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> imagens = new ArrayList<>();        //

    public void setImagens(List<MultipartFile> imagens) {           //o multipartfile precisa do setter, porque a
                                                                    // desserialização é diferente do json
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}