class Typography {
    public void justify(String label, String value) {
        int width = 60;
        int separator = 15;
        System.out.print(String.format("%-" + separator + "s: %" + (width - separator - 2) + "s\n", label, value));
    }

    public static void center(String text) {
        int tengah = (60 - text.length()) / 2;        
        for (int i = 0; i < tengah; i++) {
            System.out.print(" ");
        }        
        System.out.println(text);
    }
}