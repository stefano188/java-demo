import static org.junit.Assert.*;

import org.junit.Test;

public class JWTNimbusManagerTest {

    @Test
    public void verify() {
        String token = "eyJhbGciOiJSU0EtT0FFUC0yNTYiLCJlbmMiOiJBMjU2R0NNIiwidHlwIjoiSldFIiwia2lkIjoiMSJ9.1m1ul8j4QDvHB1RdyzElD57ekFn9FdXnxhPFANqP8Rcxk8ahc2sX8tqdFLBr837V6yzVwWrBfs4zXyrUxiDOMefiZM42Retdi4FJsXnD1IXnqLTwYlyqSu7wG95xypvM2d5r892W5hLIhoo3_DVCus78PJcuKs9-PGmNumgbjT4hI6FROmmaNNHjPTpwB5zaoR7Dcu8E90QE-8-G6fLf95IA-ztjq1JHozu8G5EuyDwS-8njzKSYGhbeY_bUi4NS1xefeC03Oy4unJL43kHgGgm_fWvFu783EQhlUeYfxyCBad_j-ZkVFExNmeXBXH0FZmuzmQRB4FI2C8GFUWme4Q.RKTSbPZsOWAE4LDs.tb_bm8HYqi8ouQ.3EPStg9vfsry9NkY08t5Bw ";
        JWTNimbusManager.verify(token);
    }

    @Test
    public void testCreate() throws Exception {

        String token = JWTNimbusManager.createToken();
        System.out.println("JWE: " + token);
        assertNotNull(token);
    }

}