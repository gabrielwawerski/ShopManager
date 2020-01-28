package sample.cash_register;

import sample.util.Util;

public final class CashRegisterHelper {
    private static final String[] NAMES = { "William Jones",
            "James Smith", "Mason Montgomery", "Taylor Harper",
            "Evelyn Evans", "Ella Johnson", "Avery Wright",
            "Jackson Robinson", "Scarlett Madison", "Carter Wyatt",
            "Jack Hughes", "Lily Green", "Eleanor Grayson",
            "Lillian Addison", "Aubrey Lewis", "Julian Harris",
            "Ellie Jaxon", "Lincoln Hazel", "Audrey Clarke",
            "Lucy Jackson", "Beverly Hudson", "Christian Hill",
    };

    public static String randomCashierName() {
        return NAMES[Util.random(0, NAMES.length)];
    }
}
