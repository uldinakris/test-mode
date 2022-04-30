package ru.netology.test;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.util.Locale;

public class DataGenerator {

    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(faker.name().username(),
                    faker.internet().password());
        }
        public static String generatePassword() {
            Faker faker = new Faker();
            return faker.internet().password();
        }
        public static String generateUsername() {
            Faker faker = new Faker();
            return faker.name().username();
        }
    }
}
