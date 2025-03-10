public class Main {
    public static void main(String[] args) {

//        String test = "HM4IO71y0loZHj6/lxwjQ54dOXF7thQfMvOka37/5lDoE8ya3GaZxVVNCd4NMVGsGDK0czIGkR7LRTzhNdVlvYGI+evjFeTJ5L0rR1BgZhvv4O7vMVEz2KH6Zt5yM41/dvmnmZRd7DuC4MvqtTLo+Zfusz7u1OKBnY4QzbaSNRLk1zd/byynxURcqQUcLCHHCa+4pwo/TYB2JjjRj7OT94JyYVAo+uvwSqehSN0+4Dad8T+0ljwSrTPwWVDG6SNAkSIeXhR9xALzaFiQzrU3xCH9JvV868HRoEGvuQy2ple4aoai0+Yjq77dPPvg1EbJaEtRBxI4JuAgpiBwxA+VurniQVwA/s6rRhbIDrYzHOokvrA0qTim/VJ+jSez8/DUmfHDYb2fHcSaiBBJlJwauGlcHhVt+fzmiaGj6NFelU+7SMIr2DVhs2hHnhggXkWwvlnujng7dUkDrpaCp4ltk9ifIcltBNKkS1vHQoHKL+LdBGxAM4srXiyK5ol9LY93PbCWD+fsPs/8Jv+ulScmII8w40cNjev5Et6FpVCrYtlgacuoaW2ruxU/tmkTpNyVwNxcr3RwRa83nC+9sHcbiQ==";
//
//        System.out.println("Result 3 : " + AESAlgorithm.decryptABankData(test));

        String LmsTestText = "\"course_name\": \"Cyber Security Awareness\",\n" +
                "\"course_type\": \"General Skill Training\",\n" +
                "\"batch_no\": \"01\",\n" +
                "\"training_category\": \"N/A\",\n" +
                "\"training_type\": \"N/A\",\n" +
                "\"training_platform\": \"N/A\",\n" +
                "\"trainer_sourse\": \"N/A\",\n" +
                "\"trainer\": \"N/A\",\n" +
                "\"training_actural_start_date\": \"01-01-2024\",\n" +
                "\"training_actural_end_date\": \"01-01-2024\",\n" +
                "\"training_time\": \"20\",\n" +
                "\"teaching_hours_per_day\": \"3\",\n" +
                "\"taining_total_hour\": \"60\",\n" +
                "\"passing_garde\": \"50\"";

        String encrypted = AESAlgorithm.encryptLMS(LmsTestText);
        System.out.println("Encrypted : " + encrypted);

        System.out.println("Decrypted :" + AESAlgorithm.decryptLMS(encrypted));

    }
}
