package ru.kucaev.filedownloader.servlets.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileListUtil {
    public static Path filesPath = Paths.get("/data").toAbsolutePath();

    public static List<String> getFileNameList() {
        if (!filesPath.toFile().exists()) filesPath.toFile().mkdir();
        return Arrays.stream(Objects.requireNonNull(filesPath.toFile().list()))
                .collect(Collectors.toList());
    }
}
