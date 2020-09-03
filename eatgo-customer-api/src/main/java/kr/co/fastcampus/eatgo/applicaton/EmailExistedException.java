package kr.co.fastcampus.eatgo.applicaton;

public class EmailExistedException extends RuntimeException{

    EmailExistedException(String email) {
        super("Email is already registered : " + email);

    }
}
