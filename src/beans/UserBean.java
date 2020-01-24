package beans;

public class UserBean {
    private String login;
    private String haslo;
    private String hasloRepeat;
    private String imie;
    private String nazwisko;
    private String mail;
    private boolean zalogowany;
    private boolean zarejestrowany;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getHasloRepeat() { return hasloRepeat; }

    public void setHasloRepeat(String hasloRepeat) {
        this.hasloRepeat = hasloRepeat;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isZalogowany() {
        return zalogowany;
    }

    public void setZalogowany(boolean zalogowany) {
        this.zalogowany = zalogowany;
    }

    public boolean isZarejestrowany() {
        return zarejestrowany;
    }

    public void setZarejestrowany(boolean zarejestrowany) {
        this.zarejestrowany = zarejestrowany;
    }


}
