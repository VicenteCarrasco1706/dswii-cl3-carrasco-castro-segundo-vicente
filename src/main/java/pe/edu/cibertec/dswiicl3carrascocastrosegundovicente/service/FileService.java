package pe.edu.cibertec.dswiicl3carrascocastrosegundovicente.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileService {
    private final Path imagesFolder = Paths.get("images");
    private final Path documentsFolder = Paths.get("documentos");

    public void guardarImagen(MultipartFile archivo) throws Exception {
        Files.copy(archivo.getInputStream(), this.imagesFolder.resolve(archivo.getOriginalFilename()));
    }

    public void guardarImagenes(List<MultipartFile> archivos) throws Exception {
        for (MultipartFile archivo : archivos) {
            this.guardarImagen(archivo);
        }
    }

    public void guardarDocumento(MultipartFile archivo) throws Exception {
        Files.copy(archivo.getInputStream(), this.documentsFolder.resolve(archivo.getOriginalFilename()));
    }

    public void guardarDocumentos(List<MultipartFile> archivos) throws Exception {
        for (MultipartFile archivo : archivos) {
            this.guardarDocumento(archivo);
        }
    }


}
