package ru.kucaev.filedownloader.servlets.servlet;

import ru.kucaev.filedownloader.servlets.service.FileListUtil;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/get_file")
public class FileDownloader extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String downloadingFileName = req.getParameter("file_name");
        Path path = Paths.get(FileListUtil.filesPath.toString(), downloadingFileName).toAbsolutePath();
        resp.setContentType("APPLICATION/OCTET-STREAM");
        resp.setHeader("Content-disposition", "attachment; filename=" + downloadingFileName);
        try(InputStream in = new FileInputStream(path.toString());
            OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[1048];
            int i;
            while ((i = in.read(buffer)) != -1) {
                out.write(buffer, 0, i);
            }
        }
    }
}