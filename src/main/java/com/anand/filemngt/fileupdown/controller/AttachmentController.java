package com.anand.filemngt.fileupdown.controller;
import com.anand.filemngt.fileupdown.entity.Attachment;
import com.anand.filemngt.fileupdown.model.ResponseData;
import com.anand.filemngt.fileupdown.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/file")
public class AttachmentController {
@Autowired
    AttachmentService attachmentService;

@PostMapping("/upload")
public ResponseEntity<ResponseData> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

    Attachment attachment = null;

    String downloadURI = "";

    attachment = attachmentService.saveAttachment(file);

    downloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/download/")
            .path(attachment.getId())
            .toUriString();


    ResponseData responseData = new ResponseData(attachment.getFileName(),
            downloadURI, file.getContentType(),
            file.getSize());

    return new ResponseEntity<>(responseData, HttpStatus.OK);
    //hello this is changed
}

@GetMapping("/download/{fileId}")
public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {

    Attachment attachment = null;
     attachment = attachmentService.getAttachment(fileId);

     return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getFileType()))
             .header(HttpHeaders.CONTENT_DISPOSITION,
                     "attachment; fileName=\"" +attachment.getFileName()
             +"\"").body(new ByteArrayResource(attachment.getData()));
}

}
