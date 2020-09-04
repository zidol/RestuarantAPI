package kr.co.fastcampus.eatgo.applicaton;

public class PasswordWrongException extends RuntimeException {
    PasswordWrongException(){
        super("Password si wrong");
    }
}
