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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String downloadingFileName = req.getParameter("file_name");
        Path path = Paths.get(FileListUtil.filesPath.toString(), downloadingFileName).toAbsolutePath();
        resp.setContentType("APPLICATION/OCTET-STREAM");
        resp.setHeader("Content-disposition", "attachment; filename=" + downloadingFileName);
        try(PrintWriter out = resp.getWriter();
            FileInputStream in = new FileInputStream(path.toString());  ) {
            int i;
            while ((i=in.read()) != -1) {
                out.write(i);
            }
        }
    }
}