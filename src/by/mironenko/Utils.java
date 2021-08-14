package by.mironenko;

import by.mironenko.domen.Roles;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Utils {
    private static Pattern emailPattern = Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$"); //точку не ищет
    private static Pattern phonePattern = Pattern.compile("^((\\+375)([1-9]{2})([1-9])([0-9]{6}))$");

    private static String SEPARATOR = "\t";

    private Utils() {
    }

    public static void emailValidation(final String email) {
        if (email == null || email.length() == 0) {
            return;
        }
        if (!emailPattern.matcher(email).matches()) {
            throw new RuntimeException("Поле Email заполнено неверно.");
        }
    }

    public static void phoneNumberValidation(final List<String> numbers) {
        if (numbers == null || numbers.size() == 0) {
            return;
        }
        numbers.stream()
                .filter(n -> !phonePattern.matcher(n).matches())
                .findFirst()
                .ifPresent(n -> {
                    throw new RuntimeException("Номер указан неверно.");
                });
    }

    public static void roleValidation(final List<Roles> roles) {
        if (roles == null || roles.size() == 0) {
            return;
        }
        final List<Integer> existingRoles = new LinkedList<>();
        final long countOfUniqueRoles = roles.stream()
                .filter(r -> !existingRoles.contains(r.getLevel()))
                .peek(r -> existingRoles.add(r.getLevel()))
                .count();
        if (roles.size() != countOfUniqueRoles || existingRoles.contains(3) && roles.size() > 1) {
            throw new RuntimeException("Роли введены неверно.");
        }
    }
}
