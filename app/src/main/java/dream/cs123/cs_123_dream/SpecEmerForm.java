package dream.cs123.cs_123_dream;

public class SpecEmerForm {

    private final String emergency_name;
    private final int id;

    public SpecEmerForm(String emergency_name, int id) {
        this.emergency_name = emergency_name;
        this.id = id;
    }

    public String getName() {
        return emergency_name;
    }

    public int getId() {
        return id;
    }

}
