package br.com.orlando.myapplication.constants;

public class DataBaseConstants {

    private DataBaseConstants() {
    }

    public static class CONTACTS {
        public static final String TABLE_NAME = "Contacts";


        public static class COLUMNS {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String EMAIL = "email";
            public static final String TELEPHONE = "telephone";
        }
    }

}
