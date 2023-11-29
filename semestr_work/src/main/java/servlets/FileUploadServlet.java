package servlets;

import interfaces.FileService;
import models.FileInfo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/uploaded/files")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    FileService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        fileService = (FileService) servletContext.getAttribute("fileService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileId = req.getParameter("id");
        FileInfo fileInfo = fileService.getFileInfo(Long.parseLong(fileId));
        resp.setContentType(fileInfo.getType());
        resp.setContentLength((int) fileInfo.getSize());
        resp.setHeader("Content-Discription", "filename=\"" + fileInfo.getOriginalFileName() + "\"");
        fileService.writeFileFromStorage(Long.parseLong(fileId), resp.getOutputStream());
        resp.flushBuffer();
    }
}
