package com.getest.game.misc;

import com.getest.game.enums.EnemyType;
import java.util.Random;

public class RandomMisc {

    public RandomMisc() {
    }

    public static EnemyType getRandomEnemyType() {
        RandomMisc.RandomEnum randomEnum = new RandomMisc.RandomEnum(EnemyType.class);
        return (EnemyType)randomEnum.random();
    }

    private static class RandomEnum<E extends Enum> {
        private static final Random RND = new Random();
        private final E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[RND.nextInt(values.length)];
        }
    }


}
