package beans;

public class ProductBean {


    private int id;
    private String nazwa;
    private String producent;
    private float cena;
    private int ilosc;
    private boolean dodany;
    private String typWyszukiwania;
    private String wprowadzonaWartosc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public boolean isDodany() {
        return dodany;
    }

    public void setDodany(boolean dodany) {
        this.dodany = dodany;
    }

    public String getTypWyszukiwania() {
        return typWyszukiwania;
    }

    public void setTypWyszukiwania(String typWyszukiwaniaNazwa) {
        this.typWyszukiwania = typWyszukiwaniaNazwa;
    }


    public String getWprowadzonaWartosc() {
        return wprowadzonaWartosc;
    }

    public void setWprowadzonaWartosc(String wprowadzonaWartosc) {
        this.wprowadzonaWartosc = wprowadzonaWartosc;
    }
}
