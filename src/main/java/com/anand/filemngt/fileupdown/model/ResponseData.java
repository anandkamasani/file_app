package com.anand.filemngt.fileupdown.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseData {

    private String fileName;
    private String downloadURL;

    private String fileType;

    private long fileSize;
}
