package com.anand.filemngt.fileupdown.service;

import com.anand.filemngt.fileupdown.entity.Attachment;
import com.anand.filemngt.fileupdown.model.ResponseData;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {

    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;
}
