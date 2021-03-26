package uz.pdp.online.appwerhouseprojectm5l11;

import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.online.appwerhouseprojectm5l11.entity.Measurement;
import uz.pdp.online.appwerhouseprojectm5l11.repository.ProductRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Main  {


    public static void main(String[] args) {




//        Measurement measurement=new Measurement();
//
//
//        Date date=new Date();
//        System.out.println(date);
//        LocalDate localDate=LocalDate.now();
//        System.out.println(localDate);

// Main main=new Main();
//        int randomNumber = main.randomNumber();
//        System.out.println();
//        int randomNumber = randomNumber();
//        System.out.println(randomNumber);


List<Integer> list=new ArrayList<>();
//list.add(2);
//list.add(9);
//if(list.size()==0){
//    System.out.println(1);
//    System.out.println(list.size());
//
//}else
//    System.out.println(list.size()+1);

//        Timestamp timestamp=Timestamp.valueOf(LocalDateTime.now());
//        LocalDateTime localDateTime=LocalDateTime.now();
//        System.out.println(timestamp);
//        System.out.println(localDateTime);
//        Date date=new Date();
//        System.out.println(date.getTime());

        UUID uuid=UUID.randomUUID();
        String s=UUID.randomUUID().toString();
        System.out.println(s);










int a=list.size()+1;
//        System.out.println(list.get(list.size()-1));





//randomNumber();

    }
    public static int randomNumber(){
        int intRandom= ThreadLocalRandom.current().nextInt();
        if (intRandom>0){
            return intRandom;
        }
        else {
            return  randomNumber();
        }
    }
}