package business.businessModel;

public enum SecurityGroup {

    None("na", "None"),
    ReceiptProvider("rp", "Receipt Provider"),
    ReceiptManager("rm", "Receipt Manager"),
    DataAnalysis("da", "Data Analysis"),
    Admin("ad", "Admin");

    private String value;
    private String name;

    SecurityGroup(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
