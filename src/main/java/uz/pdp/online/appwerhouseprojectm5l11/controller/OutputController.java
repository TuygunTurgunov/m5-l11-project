package uz.pdp.online.appwerhouseprojectm5l11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Output;
import uz.pdp.online.appwerhouseprojectm5l11.payload.OutputDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.OutputService;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping
    public Result add(@RequestBody OutputDto outputDto){
        Result result = outputService.add(outputDto);
        return result;
    }

    @GetMapping
    public Page<Output> outputPage(@RequestParam Integer page){
        Page<Output> outputPage = outputService.outputPage(page);
        return outputPage;

    }

    @GetMapping("/{id}")
    public Output oneOutput(@PathVariable Integer id){

        Output oneOutput = outputService.oneOutput(id);
        return oneOutput;
    }
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody OutputDto outputDto){

        Result result = outputService.edit(id, outputDto);
        return result;

    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = outputService.delete(id);
        return result;
    }

}
