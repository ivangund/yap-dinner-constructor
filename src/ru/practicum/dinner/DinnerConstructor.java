package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    private final Random random;

    private final HashMap<String, ArrayList<String>> dishes;

    public DinnerConstructor() {
        dishes = new HashMap<>();
        random = new Random();
    }

    public HashMap<String, ArrayList<String>> getDishes() {
        return dishes;
    }

    public void addDish(String dishType, String dishName) {
        if (!dishes.containsKey(dishType)) {
            ArrayList<String> typeDishes = new ArrayList<>();
            typeDishes.add(dishName);
            dishes.put(dishType, typeDishes);
        } else {
            dishes.get(dishType).add(dishName);
        }
    }

    public ArrayList<ArrayList<String>> generateCombos(int count, ArrayList<String> dishTypes) {
        ArrayList<ArrayList<String>> comboList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            ArrayList<String> currentCombo = new ArrayList<>();

            for (String dishType : dishTypes) {
                ArrayList<String> currentTypeDishes = dishes.get(dishType);
                currentCombo.add(currentTypeDishes.get(random.nextInt(currentTypeDishes.size())));
            }

            comboList.add(currentCombo);
        }

        return comboList;
    }
}
