package com.anand.filemngt.fileupdown.repository;

import com.anand.filemngt.fileupdown.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String> {
}
