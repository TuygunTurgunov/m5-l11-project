package uz.pdp.online.appwerhouseprojectm5l11.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Attachment;
import uz.pdp.online.appwerhouseprojectm5l11.entity.AttachmentContent;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.AttachmentContentRepository;
import uz.pdp.online.appwerhouseprojectm5l11.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file!=null){


        //////
        Attachment attachment=new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setContentType(file.getContentType());
        attachment.setSize(file.getSize());
        Attachment savedAttachment = attachmentRepository.save(attachment);


        //////Bytes[]
        AttachmentContent attachmentContent=new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
            return new Result("File saved",true,savedAttachment.getId());
        }
        return new Result("Error  saved file",false);


    }
    @SneakyThrows
    public void  downloadFile(Integer id, HttpServletResponse response){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
            if (optionalAttachmentContent.isPresent()){
                AttachmentContent attachmentContent = optionalAttachmentContent.get();
                response.setHeader("Content-Disposition","attachment; filename=\""+attachment.getName()+"\"");

                response.setContentType(attachment.getContentType());

                FileCopyUtils.copy(attachmentContent.getBytes(),response.getOutputStream());

            }
        }
    }

    public Result deleteAttachment(Integer attachmentId){

            Optional<AttachmentContent> attachmentContent = attachmentContentRepository.findByAttachmentId(attachmentId);
            if (attachmentContent.isPresent()){
                AttachmentContent attachmentContent1 = attachmentContent.get();
                attachmentContentRepository.delete(attachmentContent1);
                attachmentRepository.deleteById(attachmentId);
                return new Result("fiel deleted",true);
            }


            return new Result("error with file",false);


    }

}