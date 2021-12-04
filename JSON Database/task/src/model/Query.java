package model;

public class Query {
    private String command;
    private int id;
    private String data;

    public Query(String query) {
        command = query.substring(0, query.indexOf(" "));
        if (!query.startsWith("set")) {
            id = Integer.parseInt(query.substring(query.indexOf(" ") + 1));
            data = "";
        } else {
            String queryMinusCommand = query.substring(query.indexOf(" ") + 1);
            id = Integer.parseInt(queryMinusCommand.substring(0, queryMinusCommand.indexOf(" ")));
            data = queryMinusCommand.substring(queryMinusCommand.indexOf(" ") + 1);
        }

    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
