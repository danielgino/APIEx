package PhoneNumbers;

public class Response {
    private boolean valid;
    private String number;
    private String local_format;
    private String international_format;
    private String country_prefix;
    private String country_code;
    private String country_name;
    private String location;
    private String carrier;
    private String line_type;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocal_format() {
        return local_format;
    }

    public void setLocal_format(String local_format) {
        this.local_format = local_format;
    }

    public String getInternational_format() {
        return international_format;
    }

    public void setInternational_format(String international_format) {
        this.international_format = international_format;
    }

    public String getCountry_prefix() {
        return country_prefix;
    }

    public void setCountry_prefix(String country_prefix) {
        this.country_prefix = country_prefix;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getLine_type() {
        return line_type;
    }

    public void setLine_type(String line_type) {
        this.line_type = line_type;
    }

    @Override
    public String toString() {

        StringBuilder sb=new StringBuilder();
        sb.append("valid: ").append(this.valid).append("\n");
        sb.append(" number: ").append(this.number ).append("\n");
        sb.append(" local_format: ").append(this.local_format ).append("\n");
        sb.append(" international_format: ").append(this.international_format ).append("\n");
        sb.append(" country_prefix: ").append(this.country_prefix ).append("\n");
        sb.append(" country_code: ").append(this.country_code).append("\n");
        sb.append(" country_name: ").append(this.country_name).append("\n");
        sb.append(" location: ").append(this.location).append("\n");
        sb.append(" carrier: ").append(this.carrier).append("\n");
        sb.append(" line_type: ").append(this.line_type).append("\n");
        return sb.toString();
    }
}
