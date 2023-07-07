package SchoolShorts.formbeans;

/**
 *
 * @author hamma
 */
public enum Answer {
    YES("Yes"),
    NO("No");
    
    public static final Answer[] ALL = { YES, NO };
    
    private final String name; 
    
    public static Answer forName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null for Answer");
        }
        if (name.toUpperCase().equals("YES")) {
            return YES;
        } else if (name.toUpperCase().equals("NO")) {
            return NO;
        }
        throw new IllegalArgumentException("Name \"" + name + "\" does not correspond to any Answer");
    }
    
    
    private Answer(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override    
    public String toString() {
        return getName();
    }
    
}
