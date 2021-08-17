package br.com.zupacademy.polyana.mercadolivre.domain.produto;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary            //qd tiver em outro ambiente é possivel usar o @Profile
public class UploadFake implements  Upload{

    @Override
    public Set<String> enviar(List<MultipartFile> imagens) {                //links para imagens enviadas
        return imagens.stream().map(imagem -> "http://bucket.io/"
                +imagem.getOriginalFilename()).collect(Collectors.toSet());
    }
}