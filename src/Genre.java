public enum Genre {
        POP,
        COUNTRY,
        CLASSICAL,
        JAZZ,
        UNKNOWN;

        public static Genre fromString(String str) {
                try {
                        return valueOf(str.toUpperCase());
                } catch (IllegalArgumentException e) {
                        return UNKNOWN;
                }
        }
}
