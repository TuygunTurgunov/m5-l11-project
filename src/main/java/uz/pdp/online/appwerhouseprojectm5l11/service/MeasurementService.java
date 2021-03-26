package uz.pdp.online.appwerhouseprojectm5l11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Measurement;
import uz.pdp.online.appwerhouseprojectm5l11.payload.Result;
import uz.pdp.online.appwerhouseprojectm5l11.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement) {

        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName)
            return  new Result("Bunday po'lchov birligi mavjud",false);

        measurementRepository.save(measurement);
        return new Result("O'lchov birligi saqlandi",true);

    }
    public List<Measurement> getMeasurementListService(){
        List<Measurement> measurementList = measurementRepository.findAll();
        return measurementList;
    }
    public  Measurement getMeasurementByIdService(Integer measurementId){

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (!optionalMeasurement.isPresent())
            return null;
        return optionalMeasurement.get();


    }

    public Result editMeasurementService(Integer measurementId,Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (!optionalMeasurement.isPresent())
            return new Result("Mesurement not found by id",false);
        Measurement editedMeasurement = optionalMeasurement.get();
        editedMeasurement.setName(measurement.getName());
        editedMeasurement.setActive(measurement.getActive());
        measurementRepository.save(editedMeasurement);
        return new Result("Measurement edited",true);

    }

    public Result deleteMeasurementService(Integer id){
        try {
            measurementRepository.deleteById(id);
            return new Result("measurement deleted",true);
        }catch (Exception e){
            return new Result("measurement not found by id",false);
        }



    }





}
