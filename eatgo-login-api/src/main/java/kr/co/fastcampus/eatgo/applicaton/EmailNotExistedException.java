package kr.co.fastcampus.eatgo.applicaton;

public class EmailNotExistedException extends RuntimeException{
    EmailNotExistedException(String email) {
        super("Email is not registered : " + email);

    }
}
