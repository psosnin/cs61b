import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    static UnionFind dset = new UnionFind(10);

    @Test
    public void TestFind() {
        dset.union(2,1);
        assertEquals(dset.find(3), 3);
        dset.union(5,3);
        dset.union(5,6);
        assertEquals(dset.find(2), dset.find(1));
    }

    @Test
    public void TestConnected() {
        dset.union(2,1);
        dset.union(5,3);
        dset.union(5,6);
        assertTrue(dset.connected(2,1));
        assertTrue(dset.connected(6,3));
        assertFalse(dset.connected(6,1));
        assertEquals(dset.sizeOf(3), 3);
    }

    @Test(expected = RuntimeException.class)
    public void TestValidate() {
        dset.find(10);
        //dset.find(-1);
    }

}
