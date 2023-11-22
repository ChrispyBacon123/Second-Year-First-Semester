
public class QPAutoTest extends AutoTest {
    protected HashTable makeTable(final int size) {
        return new QPHashTable(size);
    }

    public static void main(final String[] args) {
        (new QPAutoTest()).run();
    }
}

// 5
// INSERT dsttho003
// PROBECOUNT
// INSERT thnmlu001
// PROBECOUNT
// INSERT blcdal002
// PROBECOUNT
// ISEMPTY
// SIZE
// DUMP
// RESETCOUNT
// CONTAINS dsttho003
// PROBECOUNT
// CONTAINS thnmlu001
// PROBECOUNT
// CONTAINS blcdal002
// PROBECOUNT
// CONTAINS anteus001
// PROBECOUNT