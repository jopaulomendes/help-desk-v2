package models.exceptions;

public class RefreshTokenExpired extends RuntimeException{
    public RefreshTokenExpired(String mensage){
        super(mensage);
    }
}
