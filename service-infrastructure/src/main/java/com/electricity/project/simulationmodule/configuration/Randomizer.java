package com.electricity.project.simulationmodule.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Randomizer {

    private static Random instance;

    public static Random getInstance() {
        if (instance == null) {
            instance = new Random();
        }
        return instance;
    }
}
