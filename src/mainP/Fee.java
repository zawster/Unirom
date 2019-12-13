package mainP;

public class Fee {
    public static Integer creditFee,id;
    String date;

    public Fee() {
        this.creditFee = 7400;
    }

    public Fee(String datee,Integer feea) {
        this.date = date;
        this.setCreditFee(feea);
        this.setDate(datee);

    }

    public void setCreditFee(Integer creditFee) {
        this.creditFee = creditFee;
    }

    public static Integer getCreditFee() {
        return creditFee;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        Fee.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
