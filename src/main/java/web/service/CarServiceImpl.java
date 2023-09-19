package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getCars(Integer count) {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("honda", 2001, "black"));
        carList.add(new Car("bmw", 2002, "white"));
        carList.add(new Car("mercedes", 2003, "grey"));
        carList.add(new Car("toyota", 2004, "red"));
        carList.add(new Car("lada", 2005, "blue"));
        return carList.stream().limit(count).collect(Collectors.toList());
    }
}
