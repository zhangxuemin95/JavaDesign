import java.util.Scanner;

public class ScannerP {
    public static void main(String[] args) {
        strStr("mississippi"
                ,"issip");
    }
        public static int strStr(String haystack, String needle) {
            if(needle.length() == 0 || haystack.length() == 0)
            {
                return (needle.length() == 0 ? 0 : -1);
            }
            char[] s = haystack.toCharArray();
            char[] t = needle.toCharArray();
            int i = 0, j = 0, k = -1;
            while(i < haystack.length() && j < needle.length())
            {
                if(s[i] == t[j])
                {
                    if(k == -1)
                    {
                        k = i;
                    }
                    i++;
                    j++;
                }
                else
                {
                    if( j == 0)
                    {
                        i++;
                        continue;
                    }
                    j = 0;
                    k = -1;
                }
            }
            if(j == needle.length()) return k;
            else return -1;
        }
}
