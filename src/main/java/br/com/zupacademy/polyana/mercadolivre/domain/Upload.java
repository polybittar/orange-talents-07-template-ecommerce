package br.com.zupacademy.polyana.mercadolivre.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public interface Upload {

    Set<String> enviar(List<MultipartFile> imagens);
}