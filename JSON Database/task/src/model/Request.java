package model;

public class Request {
    private String type;
    private String key;
    private String value;

    public Request(String type, String key, String value) {
        this.type = type;
        this.key = key;
        this.value = value;

    }

    /*public Request(String query) {
        type = query.substring(0, query.indexOf(" "));
        if (!query.startsWith("set")) {
            key = Integer.parseInt(query.substring(query.indexOf(" ") + 1));
            data = "";
        } else {
            String queryMinusCommand = query.substring(query.indexOf(" ") + 1);
            key = Integer.parseInt(queryMinusCommand.substring(0, queryMinusCommand.indexOf(" ")));
            data = queryMinusCommand.substring(queryMinusCommand.indexOf(" ") + 1);
        }

    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
