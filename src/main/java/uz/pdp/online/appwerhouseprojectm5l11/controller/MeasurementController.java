package uz.pdp.online.appwerhouseprojectm5l11.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Measurement;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){

        Result result = measurementService.addMeasurementService(measurement);
        return  result;

    }

    //GET LIST
    @GetMapping
    public List<Measurement>getMeasurementListController(){
        List<Measurement> measurementList = measurementService.getMeasurementListService();
        return measurementList;
    }

    //GET ONE
    @GetMapping("/{getMeasurementById}")
    public Measurement getMeasurementByIdController(@PathVariable Integer getMeasurementById){
        return measurementService.getMeasurementByIdService(getMeasurementById);
    }

    //Edit
    @PutMapping("/{editById}")
    public Result editMeasurementController(@PathVariable Integer editById,@RequestBody Measurement measurement){
        Result result = measurementService.editMeasurementService(editById, measurement);
        return result;
    }

    //Delete
    @DeleteMapping("/{deleteById}")
    public Result deleteMeasurementByIdController(@PathVariable Integer deleteById){
        Result result = measurementService.deleteMeasurementService(deleteById);
        return result;
    }

    // GET LIST , GET ONE, EDIT, DELETE




}