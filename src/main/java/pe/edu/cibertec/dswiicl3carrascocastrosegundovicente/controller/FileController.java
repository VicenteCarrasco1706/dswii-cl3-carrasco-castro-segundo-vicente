package pe.edu.cibertec.dswiicl3carrascocastrosegundovicente.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.dswiicl3carrascocastrosegundovicente.model.response.ResponseFile;
import pe.edu.cibertec.dswiicl3carrascocastrosegundovicente.service.FileService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/file")
public class FileController {
    private static final String DOCUMENTS_DIRECTORY = "documentos";
    private static final long MAX_FILE_SIZE = (long) (1024 * 1024 * 1.5);

    private FileService fileService;

    @PostMapping("/filesimages")
    public ResponseEntity<ResponseFile> subirArchivos(
            @RequestParam("files") List<MultipartFile> files) throws Exception {

        fileService.guardarImagenes(files);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseFile.builder().message("Los archivos fueron cargados correctamente").build());
    }

    @PostMapping("/filesexcel")
    public ResponseEntity<ResponseFile> subirArchivosExcel(
            @RequestParam("files") List<MultipartFile> files) throws Exception {

        // Verificar que todos los archivos sean de tipo XLSX y que no excedan el tamaño máximo
        for (MultipartFile file : files) {
            if (!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ResponseFile.builder().message("Solo se permiten archivos XLSX").build());
            }

            if (file.getSize() > MAX_FILE_SIZE) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ResponseFile.builder().message("El tamaño del archivo excede el límite permitido").build());
            }
        }

        // Guardar archivos en el directorio Documentos
        for (MultipartFile file : files) {
            Path filePath = Path.of(DOCUMENTS_DIRECTORY, file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseFile.builder().message("Los archivos XLSX fueron cargados correctamente").build());
    }


}
