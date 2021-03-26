package uz.pdp.online.appwerhouseprojectm5l11.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Input;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Warehouse;
import uz.pdp.online.appwerhouseprojectm5l11.payload.InputDto;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public Result add(@RequestBody InputDto inputDto) {
        Result result = inputService.add(inputDto);
        return result;
    }

    @GetMapping
    public Page<Input> warehousePage(@RequestParam Integer page) {
        Page<Input> inputs = inputService.getInputPage(page);
        return inputs;
    }

    @GetMapping("/{id}")
    public Input oneInput(@PathVariable Integer id) {
        Input oneInput = inputService.getOneInput(id);
        return oneInput;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody InputDto inputDto){
        Result result = inputService.edit(id, inputDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = inputService.delete(id);
        return result;
    }


}
