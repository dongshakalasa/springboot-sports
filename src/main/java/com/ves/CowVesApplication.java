package com.ves;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ves.Mapper")
public class CowVesApplication {

    public static void main(String[] args) {
       try{
           SpringApplication.run(CowVesApplication.class, args);
       }catch(Exception ex){
           ex.printStackTrace();
       }
    }

}
