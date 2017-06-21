package br.com.controlmore.vh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;

public class UpFileViewHelper implements IViewHelper{

	/**
	 * Codigo from 
	 * http://www.codejava.net/java-ee/servlet/apache-commons-fileupload-example-with-servlet-and-jsp
	 */
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String acao = request.getParameter("acao");
		String excelFilePath = null;
		// para salvar o arquivo
	    String UPLOAD_DIRECTORY = "upload";
	 
	    // configurações do arquivo
	    int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
	    int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	    int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	    
	    // Checando se a request realmente tem um arquivo
        if (!ServletFileUpload.isMultipartContent(request)) {
            // se não tiver, retorna nulo 
            return null;
        }
 
        // configurando o arquivo
        DiskFileItemFactory factory = new DiskFileItemFactory();
        
        // limite do tamanho do arquivo
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // setando local temporário para salvar o arquivo
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // tamanho maximo do arquivo
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // tamanho maximo da request
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIRECTORY;
         
        // cria o diretorio se não existir
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
 
                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("message",
                            "Upload has been done successfully!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }
        // redirects client to message page
		
		return null;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

}
