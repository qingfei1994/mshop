/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description		: just test
 * <p/>
 * <br><br>Time		: 2015/5/9 16:13
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class Test {

    public static void main(String args[]) {
        String regular = "^q_(like|eq|gt|ge|lt|le)_(s|n|d)_[A-Za-z]+[A-Za-z0-9_]+$";
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher("q_like_s_title");
        boolean match = matcher.matches();

        System.out.println(match);
    }

}
