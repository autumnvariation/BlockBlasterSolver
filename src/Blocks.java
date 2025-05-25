import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Blocks {
    public static final Map<Integer, int[][]> BLOCKS = build();
    private static Map<Integer, int[][]> build(){
        Map<Integer, int[][]> m = new HashMap<>();

        m.put(0, new int[][]{
                {1}
        });
        m.put(1, new int[][]{
                {1},
                {1},
        });
        m.put(2, new int[][]{
                {1,1}
        });
        m.put(3, new int[][]{
                {1,0},
                {0,1},
        });
        m.put(4, new int[][]{
                {0,1},
                {1,0},
        });

        m.put(5, new int[][]{
                {1,1,1}
        });
        m.put(6, new int[][]{
                {1},
                {1},
                {1}
        });
        m.put(7, new int[][]{
                {1,1},
                {1,0}
        });
        m.put(8, new int[][]{
                {1,1},
                {0,1}
        });
        m.put(9, new int[][]{
                {1,0},
                {1,1},

        });
        m.put(10, new int[][]{
                {0,1},
                {1,1}
        });
        m.put(11, new int[][]{
                {1,0,0},
                {0,1,0},
                {0,0,1}
        });
        m.put(12, new int[][]{
                {0,0,1},
                {0,1,0},
                {1,0,0}
        });
        m.put(13, new int[][]{
                {1},
                {1},
                {1},
                {1}
        });
        m.put(14, new int[][]{
                {1,1,1,1}
        });
        m.put(15, new int[][]{
                {1,1},
                {1,1}
        });
        m.put(16, new int[][]{
                {1,0},
                {1,0},
                {1,1}
        });
        m.put(17, new int[][]{
                {0,1},
                {0,1},
                {1,1}
        });
        m.put(18, new int[][]{
                {1,1},
                {0,1},
                {0,1}
        });
        m.put(19, new int[][]{
                {1,1},
                {1,0},
                {1,0}
        });
        m.put(20, new int[][]{
                {1,1,1},
                {0,0,1}
        });
        m.put(21, new int[][]{
                {1,1,1},
                {1,0,0}
        });
        m.put(22, new int[][]{
                {1,0,0},
                {1,1,1}
        });
        m.put(23, new int[][]{
                {0,0,1},
                {1,1,1}
        });
        m.put(24, new int[][]{
                {0,1,1},
                {1,1,0}
        });
        m.put(25, new int[][]{
                {1,0},
                {1,1},
                {0,1}
        });
        m.put(26, new int[][]{
                {0,1},
                {1,1},
                {1,0}
        });
        m.put(27, new int[][]{
                {1,1,0},
                {0,1,1}
        });
        m.put(28, new int[][]{
                {0,1,0},
                {1,1,1}
        });
        m.put(29, new int[][]{
                {1,1,1},
                {0,1,0}
        });
        m.put(30, new int[][]{
                {1,0},
                {1,1},
                {1,0}
        });
        m.put(31, new int[][]{
                {0,1},
                {1,1},
                {0,1}
        });
        m.put(32, new int[][]{
                {1,1,1},
                {1,1,1}
        });
        m.put(33, new int[][]{
                {1,1},
                {1,1},
                {1,1}
        });
        m.put(34, new int[][]{
                {1,1,1},
                {1,1,1},
                {1,1,1}
        });
        m.put(35, new int[][]{
                {1},
                {1},
                {1},
                {1},
                {1}
        });
        m.put(36, new int[][]{
                {1,1,1,1,1}
        });
        m.put(37, new int[][]{
                {1,1,1},
                {1,0,0},
                {1,0,0}
        });
        m.put(38, new int[][]{
                {1,0,0},
                {1,0,0},
                {1,1,1}
        });
        m.put(39, new int[][]{
                {0,0,1},
                {0,0,1},
                {1,1,1}
        });
        m.put(40, new int[][]{
                {1,1,1},
                {0,0,1},
                {0,0,1}
        });


        return Collections.unmodifiableMap(m);
    }
}
