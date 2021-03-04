package com.example.demo.batch;

import com.example.demo.Coffee;
import org.springframework.batch.item.ItemProcessor;


class CoffeeItemProcessor implements ItemProcessor<Coffee, Coffee> {

    @Override
    public Coffee process(Coffee coffee) throws Exception {
        String brand = coffee.getBrand().toUpperCase();
        String origin = coffee.getOrigin().toUpperCase();
        String characteristics = coffee.getCharacteristics().toUpperCase();

        return new Coffee(brand, origin, characteristics);
    }
}
