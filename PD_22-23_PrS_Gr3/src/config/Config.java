package config;

import java.util.concurrent.TimeUnit;

public class Config {

    // Porta e serverit
    public static final int SERVER_PORT = 2010;

    // Porta e klientit
    public static final int CLIENT_PORT = 2023;

    // Emri i shërbimit të serverit
    public static final String SERVER_SERVICE_NAME = "ScheduleServer";

    // Emri i shërbimit të klientit
    public static final String CLIENT_SERVICE_NAME = "ScheduleClient";

    // Fjalëkalimi i lidhjes për serverin
    public static final String SERVER_CONNECTION_PASSWORD = "123456";

    // Intervali i kontrollit
    public static final int CHECK_INTERVAL = 4000;

    // Njësia e kohës për intervalin e kontrollit
    public static final TimeUnit CHECK_INTERVAL_UNIT = TimeUnit.MILLISECONDS;
}
