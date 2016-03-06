package nl.rwslinkman.awesomeexample;

/**
 * @author Rick Slinkman
 */
public class AwesomeExample
{
    private String mAwesomeName;
    private int mAwesomeRes;

    public AwesomeExample(String name, int res)
    {
        this.mAwesomeName = name;
        this.mAwesomeRes = res;
    }

    @Override
    public boolean equals(Object o)
    {
        return o instanceof AwesomeExample && getAwesomeRes() == ((AwesomeExample) o).getAwesomeRes();
    }

    public String getAwesomeName() {
        return mAwesomeName;
    }

    public int getAwesomeRes() {
        return mAwesomeRes;
    }
}
