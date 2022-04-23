package ru.netology.test;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.util.Locale;

public class DataGenerator {

    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale, String status) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(faker.name().username(),
                    faker.internet().password(),
                    faker.options().option(status));
        }
    }
}
