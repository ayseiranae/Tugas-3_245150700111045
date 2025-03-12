import java.security.SecureRandom;

public class Tracking {
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int TRACKING_LENGTH = 10; 
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateTrackingNumber() {
        StringBuilder sb = new StringBuilder("NDH");
        for (int i = 0; i < TRACKING_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}