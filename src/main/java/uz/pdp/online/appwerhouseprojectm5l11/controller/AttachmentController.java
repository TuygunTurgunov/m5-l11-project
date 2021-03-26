package uz.pdp.online.appwerhouseprojectm5l11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

@PostMapping("/upload")
public Result upload(MultipartHttpServletRequest request){

    Result result = attachmentService.uploadFile(request);
    return result;
}
    @GetMapping("/download/{id}")
    public void downloadFile(@PathVariable Integer id, HttpServletResponse response){

    attachmentService.downloadFile(id,response);

    }
    @DeleteMapping("{id}")
    public Result deleteAttachment(@PathVariable Integer id){
        Result result = attachmentService.deleteAttachment(id);
        return result;
    }





}
