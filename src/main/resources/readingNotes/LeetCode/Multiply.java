import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Multiply {
    @Test
    public void testMultiply() {
        String num1 = "1531321345495654";
        String num2 = "2845132465462137";
        
        long start = System.nanoTime();
        String result = multiply(num1, num2);
        System.out.println(System.nanoTime() - start);
        System.out.println(result);
    }
    
    private String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        
        int v1;
        int v2;
        
        int[] res = new int[num1.length() + num2.length()];
        for(int i = num1.length() - 1; i >= 0; i--) {
            v1 = num1.charAt(i) - '0';
            for(int j = num2.length() - 1; j >= 0; j--) {
                v2 = num2.charAt(j) - '0';
                res[i + j + 1] += v1 * v2;
            }
        }
        
        int v;
        for(int i = res.length - 1; i > 0; i--) {
            v = res[i];
            res[i - 1] += v / 10;
            res[i] = v % 10;
        }
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < res.length; i++) {
            if(i == 0 && res[i] == 0) {
                continue;
            }
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
