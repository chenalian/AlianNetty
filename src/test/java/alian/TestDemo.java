/**
 * @program: AlianNetty
 * @author: alian
 * @description: 测试
 * @create: 2022-04-14 20:03
 **/

package alian;

import java.util.Arrays;
import java.util.Comparator;

public class TestDemo {
//      final Integer a=3;
    public static void main(String[] args) {
        m1();

    }

    private static void m1() {
        Integer[] i=new Integer[]{1,2,3,1};
        Arrays.sort(i, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                System.out.println(a);
                return 0;
            }
        });
    }
}
