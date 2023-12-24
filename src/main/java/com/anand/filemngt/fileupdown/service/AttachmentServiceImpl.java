package com.anand.filemngt.fileupdown.service;

import com.anand.filemngt.fileupdown.entity.Attachment;
import com.anand.filemngt.fileupdown.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements AttachmentService{
    @Autowired
    AttachmentRepository repository;
    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(fileName.contains("..")){
                throw new Exception("filename contains invalid path sequence " +fileName);
            } Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
            return repository.save(attachment);

        }catch(Exception e)  {
         throw new Exception("Could not save File "+fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {

        Attachment attachment = repository.findById(fileId)
                .orElseThrow(() -> new Exception("file is not found with this id " + fileId));

        return attachment;
    }


}
