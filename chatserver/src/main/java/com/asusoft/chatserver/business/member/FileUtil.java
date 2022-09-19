package com.asusoft.chatserver.business.member;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtil {

    private final String rootPath = "./uploads";

    private Path getRootPath() {
        return Paths.get(rootPath).toAbsolutePath().normalize();
    }

    private Path getPath(String path) {
        return Paths.get(rootPath + "/" + path).toAbsolutePath().normalize();
    }

    private void createRootDirectory() throws IOException {
        Path fileLocation = getRootPath();
        Files.createDirectories(fileLocation);
    }

    private void createDirectory(String path) throws IOException {
        Path fileLocation = getPath(path);
        Files.createDirectories(fileLocation);
    }

    public String upload(Long memberId, MultipartFile file) throws IOException {
        String fileName = memberId + ".jpg";
        createDirectory(memberId.toString());
        Path path = getRootPath().resolve(memberId.toString()).resolve(fileName).normalize();
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/member")
                .path("/file")
                .path("/" + memberId)
                .path("/" + fileName)
                .toUriString();
    }

    public Resource download(Long memberId, String fileName) throws MalformedURLException {
        Path path = getRootPath().resolve(memberId.toString()).resolve(fileName).normalize();
        return new UrlResource(path.toUri());
    }
}
