import java.lang.reflect.Method;

/**
 * Created by Kyler on 3/14/2015.
 */
public class MethodHolder {

    private String name;
    private String block;
    private int start;
    private int end;

    public MethodHolder() {}

    public MethodHolder (String name, String block, int start, int end) {
        this.name = name;
        this.block = block;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
